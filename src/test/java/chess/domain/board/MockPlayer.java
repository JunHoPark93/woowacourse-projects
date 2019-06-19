package chess.domain.board;


import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Map;
import java.util.Optional;

public class MockPlayer implements Player {
    private final PieceColor color;
    private Map<Square, Piece> pieces;

    public MockPlayer(PieceColor color, Map<Square, Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }
    @Override
    public int getPiecesCount() {
        return 0;
    }

    @Override
    public Optional<Piece> getPiece(Square source) {
        return Optional.ofNullable(pieces.get(source));
    }

    @Override
    public boolean contains(Vector vector) {
        return pieces.containsKey(vector.getSquare());
    }
}
