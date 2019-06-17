package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class SquareFactory {
    private static final List<Square> boards;

    static {
        boards = new ArrayList<>();
        for (XPosition xPosition : XPositionFactory.getXPositions()) {
            for (YPosition yPosition : YPositionFactory.getYPositions()) {
                boards.add(new Square(xPosition, yPosition));
            }
        }
    }

    public static List<Square> getBoards() {
        return boards;
    }
}
