package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.Set;

public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return null;
    }
}
