package domain;

import java.util.ArrayList;
import java.util.List;

public class ReserveStatusList {
    private List<ReserveStatus> reserveStatuses;

    public ReserveStatusList() {
        reserveStatuses = new ArrayList<>();
    }

    public void putResult(ReserveStatus reserveStatus) {
        if (!checkValidTimeRange(reserveStatus)) {
            throw new IllegalArgumentException("두 영화 시간 차이가 1시간 이내입니다.");
        }
        reserveStatuses.add(reserveStatus);
    }

    private boolean checkValidTimeRange(ReserveStatus reserveStatus) {
        return reserveStatuses.stream().noneMatch(status -> status.isOneHourOverLap(reserveStatus));
    }

    public void printResult() {
        for (ReserveStatus status : reserveStatuses) {
            System.out.println(status.toString());
        }
    }
}
