package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.Optional;
import java.util.Set;

public interface Player {
    int getPiecesCount();

    Optional<Piece> getPiece(Square source);

    boolean contains(Vector vector);

    Set<Square> getKingPath();

    void move(Square source, Square target);

    Piece remove(Square target);

    double score();
}
