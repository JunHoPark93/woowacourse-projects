package next.dto;

public class UserUpdatedDto {
    private String name;
    private String email;

    private UserUpdatedDto() {
    }

    public UserUpdatedDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
