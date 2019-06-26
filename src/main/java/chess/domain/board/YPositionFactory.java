package chess.domain.board;

import java.util.*;

public class YPositionFactory {
    private static final List<String> type = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
    private static final Map<String, Integer> map = new HashMap<>();
    private final static List<YPosition> yPositions = new ArrayList<>();
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 7;

    static {
        for (int i = 0; i < type.size(); i++) {
            map.put(type.get(i), i);
        }

        for (String s : type) {
            yPositions.add(new YPosition(s));
        }
    }

    public static YPosition down(String yPosition, int moveCnt) {
        int targetIdx = index(yPosition) - moveCnt;
        if (targetIdx < START_INDEX) {
            return yPositions.get(index(yPosition));
        }

        return yPositions.get(targetIdx);
    }

    public static YPosition up(String yPosition, int moveCnt) {
        int targetIdx = index(yPosition) + moveCnt;
        if (targetIdx > END_INDEX) {
            return yPositions.get(index(yPosition));
        }

        return yPositions.get(targetIdx);
    }

    public static List<YPosition> upToEnd(String yPosition) {
        List<YPosition> yPositionList = new ArrayList<>();
        int index = index(yPosition) + 1;

        while (index <= END_INDEX) {
            yPositionList.add(yPositions.get(index));
            index++;
        }

        return yPositionList;
    }

    public static List<YPosition> downToEnd(String yPosition) {
        List<YPosition> yPositionList = new ArrayList<>();
        int index = index(yPosition) - 1;

        while (index >= START_INDEX) {
            yPositionList.add(yPositions.get(index));
            index--;
        }

        return yPositionList;
    }

    private static int index(String yPosition) {
        return map.get(yPosition);
    }
}
