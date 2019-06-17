package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class SquareFactory {
    public static final List<Square> boards;

    static {
        boards = new ArrayList<>();
        for (XPosition xPosition : XPositionFactory.xPositions) {
            for (YPosition yPosition : YPositionFactory.yPositions) {
                boards.add(new Square(xPosition, yPosition));
            }
        }
    }
}
