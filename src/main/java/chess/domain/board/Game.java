package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final ChessObserver observer;
    private PieceColor turn;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = PieceColor.BLACK;
        this.observer = new ChessObserver();
    }

    public int whitePiecesCount() {
        return whitePlayer.pieceCount();
    }

    public int blackPiecesCount() {
        return blackPlayer.pieceCount();
    }

    public Piece getPiece(Square source) {
        return blackPlayer.piece(source)
                .orElseGet(() -> whitePlayer.piece(source)
                        .orElseThrow(IllegalArgumentException::new));
    }

    public Set<Vector> moveList(Square source) {
        Piece piece = getPiece(source);
        checkTurn(piece);

        Set<Vector> moveList = piece.movableList(source);

        if (!(piece instanceof Knight)) {
            removeObstacles(moveList);
        }

        if (piece instanceof King) {
            moveList = removeKingPath(moveList);
        }

        if (piece instanceof Pawn) {
            moveList = removePawnPath(moveList);
        }

        return moveList.stream()
                .filter(vector -> !(currentPlayer().contains(vector)))
                .collect(Collectors.toSet());
    }

    private Set<Vector> removePawnPath(Set<Vector> moveList) {
        Set<Vector> crossTarget = moveList.stream()
                .filter(vector -> vector.isDownLeft() ||
                        vector.isDownRight() ||
                        vector.isUpLeft() ||
                        vector.isUpRight())
                .filter(vector -> !(opponentPlayer().contains(vector)))
                .collect(Collectors.toSet());

        Set<Vector> linearTarget = moveList.stream()
                .filter(vector -> vector.isUp() ||
                        vector.isDown())
                .filter(vector -> (opponentPlayer().contains(vector)))
                .collect(Collectors.toSet());

        moveList.removeAll(crossTarget);
        moveList.removeAll(linearTarget);

        return moveList;
    }

    private Set<Vector> removeKingPath(Set<Vector> moveList) {
        Set<Vector> kingPath = opponentPlayer().kingPath();

        return moveList.stream()
                .filter(vector -> !vector.containsSameSquare(kingPath))
                .collect(Collectors.toSet());
    }

    private void removeObstacles(Set<Vector> moveList) {
        Set<Vector> existingList = new HashSet<>();
        for (Vector vector : moveList) {
            if (blackPlayer.contains(vector)) {
                existingList.add(vector);
            }

            if (whitePlayer.contains(vector)) {
                existingList.add(vector);
            }
        }

        for (Vector vector : existingList) {
            Set<Vector> vectors = vector.vectorList();
            moveList.removeAll(vectors);
        }
    }

    private void checkTurn(Piece piece) {
        if (!piece.isSameColor(turn)) {
            throw new IllegalArgumentException("맞는 턴이 아닙니다");
        }
    }

    private Player currentPlayer() {
        if (turn.equals(PieceColor.BLACK)) {
            return blackPlayer;
        }

        return whitePlayer;
    }

    private Player opponentPlayer() {
        if (turn.equals(PieceColor.BLACK)) {
            return whitePlayer;
        }

        return blackPlayer;
    }

    public boolean move(Square source, Square target) {
        Piece sourcePiece = getPiece(source);
        checkTurn(sourcePiece);
        checkTarget(source, target);

        currentPlayer().move(source, target);

        if (opponentPlayer().piece(target).isPresent()) {
            boolean playing = takePiece(target);
            turn = turn.toggle();
            return playing;
        }
        turn = turn.toggle();
        return true;
    }

    private boolean takePiece(Square target) {
        Piece deadPiece = opponentPlayer().remove(target);
        observer.take(deadPiece);

        return !(deadPiece instanceof King);
    }

    private void checkTarget(Square source, Square target) {
        Set<Vector> movableList = moveList(source);
        movableList.stream()
                .filter(vector -> vector.isSameSquare(target))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public GameResult score() {
        double blackScore = blackPlayer.score();
        double whiteScore = whitePlayer.score();

        return new GameResult(blackScore, whiteScore);
    }

    public PieceColor getTurn() {
        return turn;
    }

    public List<Piece> getDeadList(PieceColor color) {
        return observer.deadList(color);
    }
}
