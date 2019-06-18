package chess.domain.piece;

import chess.domain.board.Square;

import java.util.Set;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public Set<Square> movableList(Square source) {
        return null;
    }
}
