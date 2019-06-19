package chess.domain.piece;

import chess.domain.board.Square;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public Set<Square> movableList(Square source) {
        Set<Square> movableList = new HashSet<>();
        movableList.add(source.moveUp(2).moveUpRight());
        movableList.add(source.moveRight(2).moveUpRight());
        movableList.add(source.moveRight(2).moveDownRight());
        movableList.add(source.moveDown(2).moveDownRight());
        movableList.add(source.moveDown(2).moveDownLeft());
        movableList.add(source.moveLeft(2).moveDownLeft());
        movableList.add(source.moveLeft(2).moveUpLeft());
        movableList.add(source.moveUp(2).moveUpLeft());


        movableList.remove(source.moveUpRight());
        movableList.remove(source.moveUpLeft());
        movableList.remove(source.moveDownRight());
        movableList.remove(source.moveDownLeft());
        movableList.remove(source);

        for (Square square : movableList) {
            if (source.isLocatedSameLine(square)) {
                movableList.remove(square);
            }
        }

        return movableList;
    }
}
