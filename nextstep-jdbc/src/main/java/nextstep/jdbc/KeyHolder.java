package nextstep.jdbc;

public class KeyHolder {
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "KeyHolder{" +
                "id=" + id +
                '}';
    }
}
