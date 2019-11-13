package sql.dto;

import java.util.Objects;

public class YearsCodingResponse {
    private String type;
    private String mean;

    public YearsCodingResponse(String type, String mean) {
        this.type = type;
        this.mean = mean;
    }

    public String getType() {
        return type;
    }

    public String getMean() {
        return mean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearsCodingResponse that = (YearsCodingResponse) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(mean, that.mean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mean);
    }
}
