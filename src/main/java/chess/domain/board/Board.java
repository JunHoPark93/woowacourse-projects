package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

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

    public Set<Square> moveList(Square source) {
        Piece piece = getPiece(source);
        if (turn != piece.getColor()) {
            throw new IllegalArgumentException("맞는 턴이 아닙니다");
        }
// TODO
//        if (piece.getColor() == PieceColor.BLACK) {
//            Set<Square> moveList = piece.movableList(source);
//            Set<Square> existingList = new HashSet<>();
//            for (Square square : moveList) {
//                if (blackPlayer.contains(square)) {
//                    existingList.add(square);
//                }
//            }
//
//            for (Square square : existingList) {
//                if (source.positionedRight(square)) { // 우측 위치
//
//                }
//
//                if (source.positionedUp(square)) {
//
//                }
//
//                if (source.positionedDown(square)) {
//
//                }
//
//                if (source.positionedLeft(square)) {
//
//                }
//
//                if (source.positionedUpRight(square)) { // 우측 대각선
//
//                }
//
//                if (source.positionedDownRight(square)) {
//
//                }
//
//                if (source.positionedDownLeft(square)) {
//
//                }
//
//                if (source.positionedUpLeft(square)) {
//
//                }
//            }
//        }



        return null;
    }
}
