package chess.domain.board;

import java.util.*;

public class XPositionFactory {
    private static final List<String> type = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
    private static final Map<String, Integer> map = new HashMap<>();
    private static final List<XPosition> xPositions = new ArrayList<>();
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 7;

    static {
        for (int i = 0; i < type.size(); i++) {
            map.put(type.get(i), i);
        }

        for (String s : type) {
            xPositions.add(new XPosition(s));
        }
    }

    public static XPosition left(String xPosition, int moveCnt) {
        int targetIdx = index(xPosition) - moveCnt;
        if (targetIdx < START_INDEX) {
            return xPositions.get(index(xPosition));
        }

        return xPositions.get(targetIdx);
    }

    public static XPosition right(String xPosition, int moveCnt) {
        int targetIdx = index(xPosition) + moveCnt;
        if (targetIdx > END_INDEX) {
            return xPositions.get(index(xPosition));
        }
        return xPositions.get(targetIdx);
    }

    public static List<XPosition> rightToEnd(String xPosition) {
        List<XPosition> xPositionList = new ArrayList<>();
        int index = index(xPosition) + 1;

        while (index <= END_INDEX) {
            xPositionList.add(xPositions.get(index));
            index++;
        }
        return xPositionList;
    }

    public static List<XPosition> leftToEnd(String xPosition) {
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
