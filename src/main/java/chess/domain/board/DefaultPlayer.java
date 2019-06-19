package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Map;
import java.util.Optional;

public class DefaultPlayer implements Player {
    private final PieceColor color;
    private Map<Square, Piece> pieces;

    public DefaultPlayer(PieceColor color) {
        this.color = color;
        this.pieces = PlayerFactory.init(color);
    }

    public int getPiecesCount() {
        return pieces.size();
    }

    @Override
    public Optional<Piece> getPiece(Square source) {
        return Optional.empty();
    }

    @Override
    public boolean contains(Vector vector) {
        return pieces.containsKey(vector.getSquare());
    }
}
