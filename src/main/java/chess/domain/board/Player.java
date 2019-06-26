package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.Optional;
import java.util.Set;

public interface Player {
    int pieceCount();

    Optional<Piece> piece(Square source);

    boolean contains(Vector vector);

    Set<Vector> kingPath();

    void move(Square source, Square target);

    Piece remove(Square target);

    double score();
}
