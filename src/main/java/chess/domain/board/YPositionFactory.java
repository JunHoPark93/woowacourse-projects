package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class YPositionFactory {
    public final static List<YPosition> yPositions;
    static {
        yPositions = new ArrayList<>();
        yPositions.add(new YPosition("1"));
        yPositions.add(new YPosition("2"));
        yPositions.add(new YPosition("3"));
        yPositions.add(new YPosition("4"));
        yPositions.add(new YPosition("5"));
        yPositions.add(new YPosition("6"));
        yPositions.add(new YPosition("7"));
        yPositions.add(new YPosition("8"));
    }
}
