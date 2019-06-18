package chess.domain.piece;

import chess.domain.board.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {
    private boolean isMoved;

    public Pawn(PieceColor color) {
        super(color);
        this.isMoved = false;
    }

    @Override
    public Set<Square> movableList(Square source) {
        Set<Square> movableList = new HashSet<>();
        movableList.add(source.moveUp(1));
        movableList.add(source.moveUpRight());
        movableList.add(source.moveUpLeft());

        if (!isMoved) {
            movableList.add(source.moveUp(2));
            isMoved = true;
        }

        movableList.remove(source);

        return movableList;
    }

    public boolean isMoved() {
        return isMoved;
    }
}
