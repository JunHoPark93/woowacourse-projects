package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class XPositionFactory {
    public static final List<XPosition> xPositions;

    static {
        xPositions = new ArrayList<>();
        xPositions.add(new XPosition("a"));
        xPositions.add(new XPosition("b"));
        xPositions.add(new XPosition("c"));
        xPositions.add(new XPosition("d"));
        xPositions.add(new XPosition("e"));
        xPositions.add(new XPosition("f"));
        xPositions.add(new XPosition("g"));
        xPositions.add(new XPosition("h"));
    }
}
