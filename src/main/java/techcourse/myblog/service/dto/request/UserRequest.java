package techcourse.myblog.service.dto.request;

import techcourse.myblog.support.validator.EqualFields;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualFields(baseField = "password", matchField = "reconfirmPassword")
public class UserRequest {
    @NotBlank(message = "이름을 작성해주세요!")
    @Pattern(regexp = "[^ !@#$%^&*(),.?\\\":{}|<>0-9]{2,10}",
            message = "이름은 2~10자로 제한하며 숫자나 특수문자가 포함될 수 없습니다!")
    private String name;

    @NotBlank(message = "이메일을 작성해주세요!")
    @Email(message = "이메일 양식을 지켜주세요!")
    private String email;

    @NotBlank(message = "비밀번호를 작성해주세요!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}",
            message = "비밀번호는 8자 이상의 소문자, 대문자, 숫자, 특수문자의 조합이어야 합니다!")
    private String password;

    @NotBlank(message = "설정한 비밀번호를 재작성해주세요!")
    private String reconfirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getReconfirmPassword() {
        return reconfirmPassword;
    }

    public void setReconfirmPassword(String reconfirmPassword) {
        this.reconfirmPassword = reconfirmPassword;
    }
}
