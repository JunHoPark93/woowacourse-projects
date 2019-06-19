package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        return Optional.ofNullable(whitePlayer.getPiece(source))
                .orElse(blackPlayer.getPiece(source))
                .orElseThrow(IllegalArgumentException::new);
    }

    public Set<Vector> moveList(Square source) {
        Piece piece = getPiece(source);
        if (turn != piece.getColor()) {
            throw new IllegalArgumentException("맞는 턴이 아닙니다");
        }

        if (piece.getColor() == PieceColor.BLACK) {
            Set<Vector> moveList = piece.movableList(source);
            Set<Vector> existingList = new HashSet<>();
            for (Vector vector : moveList) {
                if (blackPlayer.contains(vector)) {
                    existingList.add(vector);
                }
            }

            for (Vector vector : existingList) {
                Set<Vector> vectors = vector.getList();
                moveList.removeAll(vectors);
            }

            return moveList;
        }

        return null;
    }
}
