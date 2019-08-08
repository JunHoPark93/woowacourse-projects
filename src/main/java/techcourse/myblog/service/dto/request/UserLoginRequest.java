package techcourse.myblog.service.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginRequest {
    @NotBlank(message = "이메일을 작성해주세요")
    @Email(message = "이메일 양식을 지켜주세요")
    private String email;

    @NotBlank(message = "비밀번호를 작성해주세요")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
