package chess.domain.piece;

import chess.domain.board.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color);
    }

    // TODO
    @Override
    public Set<Square> movableList(Square source) {
        List<Square> movableList = new ArrayList<>();
        movableList.add(source.moveUp(3).moveRight(1));

        movableList.addAll(source.moveUpToEnd());
        movableList.addAll(source.moveDownToEnd());
        movableList.addAll(source.moveLeftToEnd());
        movableList.addAll(source.moveRightToEnd());

        return null;
    }
}
