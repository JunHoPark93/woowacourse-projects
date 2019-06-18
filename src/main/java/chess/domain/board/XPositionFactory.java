package chess.domain.board;

import java.util.*;

public class XPositionFactory {
    // TODO : type, map 리팩토링 필요
    private static final List<String> type = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
    private static final Map<String, Integer> map = new HashMap<>();
    private static final List<XPosition> xPositions;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 7;

    static {
        for (int i = 0; i < type.size() ; i++) {
            map.put(type.get(i), i);
        }

        xPositions = new ArrayList<>();
        for (String s : type) {
            xPositions.add(new XPosition(s));
        }
    }

    public static List<XPosition> getXPositions() {
        return xPositions;
    }

    public static XPosition moveLeft(String xPosition, int moveCnt) {
        int targetIdx = index(xPosition) - moveCnt;
        if (targetIdx < START_INDEX) {
            return xPositions.get(START_INDEX);
        }

        return xPositions.get(targetIdx);
    }

    public static XPosition moveRight(String xPosition, int moveCnt) {
        int targetIdx = index(xPosition) + moveCnt;
        if (targetIdx > END_INDEX) {
            return xPositions.get(END_INDEX);
        }
        return xPositions.get(targetIdx);
    }

    public static List<XPosition> moveRightToEnd(String xPosition) {
        List<XPosition> xPositionList = new ArrayList<>();
        int index = index(xPosition) + 1;

        while (index <= END_INDEX) {
            xPositionList.add(xPositions.get(index));
            index++;
        }
        return xPositionList;
    }

    public static List<XPosition> moveLeftToEnd(String xPosition) {
        List<XPosition> xPositionList = new ArrayList<>();
        int index = index(xPosition) - 1;

        while (index >= START_INDEX) {
            xPositionList.add(xPositions.get(index));
            index--;
        }
        return xPositionList;
    }

    private static int index(String xPosition) {
        return map.get(xPosition);
    }
}
