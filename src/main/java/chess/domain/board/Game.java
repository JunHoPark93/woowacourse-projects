package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    private final Player whitePlayer;
    private final Player blackPlayer;
    // TODO ChessObserver 이름 고민
    private final ChessObserver observer;
    private PieceColor turn;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = PieceColor.BLACK;
        this.observer = new ChessObserver();
    }

    public int whitePiecesCount() {
        return whitePlayer.getPiecesCount();
    }

    public int blackPiecesCount() {
        return blackPlayer.getPiecesCount();
    }

    public Piece getPiece(Square source) {
        // TODO if문 2개 해결
        Optional<Piece> piece = blackPlayer.getPiece(source);
        if (!piece.isPresent()) {
            piece = whitePlayer.getPiece(source);
        }

        Optional<Piece> returnPiece = Optional.ofNullable(piece).orElseThrow(IllegalArgumentException::new);

        if (returnPiece.isPresent()) {
            return returnPiece.get();
        }

        throw new IllegalArgumentException();
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
                .filter(vector -> vector.getDirection().equals(Direction.DOWN_LEFT) ||
                        vector.getDirection().equals(Direction.DOWN_RIGHT) ||
                        vector.getDirection().equals(Direction.UP_LEFT) ||
                        vector.getDirection().equals(Direction.UP_RIGHT))
                .filter(vector -> !(opponentPlayer().contains(vector)))
                .collect(Collectors.toSet());

        Set<Vector> linearTarget = moveList.stream()
                .filter(vector -> vector.getDirection().equals(Direction.UP) ||
                        vector.getDirection().equals(Direction.DOWN))
                .filter(vector -> (opponentPlayer().contains(vector)))
                .collect(Collectors.toSet());

        moveList.removeAll(crossTarget);
        moveList.removeAll(linearTarget);

        return moveList;
    }

    private Set<Vector> removeKingPath(Set<Vector> moveList) {
        Set<Square> kingPath = opponentPlayer().getKingPath();
        return moveList.stream().filter(vector -> !kingPath.contains(vector.getSquare())).collect(Collectors.toSet());

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
            Set<Vector> vectors = vector.getList();
            moveList.removeAll(vectors);
        }
    }

    private void checkTurn(Piece piece) {
        if (turn != piece.getColor()) {
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

        if (opponentPlayer().getPiece(target).isPresent()) {
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
                .filter(vector -> vector.getSquare().equals(target))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public Score score() {
        double blackScore = blackPlayer.score();
        double whiteScore = whitePlayer.score();

        return new Score(blackScore, whiteScore);
    }

    public PieceColor getTurn() {
        return turn;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public List<Piece> getDeadList(PieceColor color) {
        return observer.getDeadList(color);
    }
}
