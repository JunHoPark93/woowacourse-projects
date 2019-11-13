package sql.dto;

import java.util.Objects;

public class HobbyResponse {
    private String cnt;
    private String ratio;

    public HobbyResponse(String cnt, String ratio) {
        this.cnt = cnt;
        this.ratio = ratio;
    }

    public String getCnt() {
        return cnt;
    }

    public String getRatio() {
        return ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyResponse response = (HobbyResponse) o;
        return Objects.equals(cnt, response.cnt) &&
                Objects.equals(ratio, response.ratio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnt, ratio);
    }
}
