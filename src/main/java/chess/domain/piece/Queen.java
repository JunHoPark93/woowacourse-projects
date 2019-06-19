package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.Set;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return null;
    }
}
