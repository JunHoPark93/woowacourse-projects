package chess.domain.piece;

import chess.domain.board.Square;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public Set<Square> movableList(Square source) {
        Set<Square> movableList = new HashSet<>();
        movableList.addAll(source.moveUpToEnd());
        movableList.addAll(source.moveDownToEnd());
        movableList.addAll(source.moveLeftToEnd());
        movableList.addAll(source.moveRightToEnd());

        return movableList;
    }
}
