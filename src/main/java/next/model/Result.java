package next.model;

public class Result {
    private boolean status;
    private String message;

    private Result(boolean status) {
        this(status, "");
    }

    private Result(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Result ok() {
        return new Result(true);
    }

    public static Result fail(String message) {
        return new Result(false, message);
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Result [status=" + status + ", message=" + message + "]";
    }
}
