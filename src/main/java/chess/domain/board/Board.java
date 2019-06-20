package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private PieceColor turn;

    public Board(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = PieceColor.BLACK;
    }

    public int whitePiecesCount() {
        return whitePlayer.getPiecesCount();
    }

    public int blackPiecesCount() {
        return blackPlayer.getPiecesCount();
    }

    public Piece getPiece(Square source) {
        // TODO
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
        return moveList.stream()
                .filter(vector -> !(currentPlayer().contains(vector)))
                .collect(Collectors.toSet());
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
}
