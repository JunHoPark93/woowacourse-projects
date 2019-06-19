package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.Optional;

public interface Player {
    int getPiecesCount();

    Optional<Piece> getPiece(Square source);
}
