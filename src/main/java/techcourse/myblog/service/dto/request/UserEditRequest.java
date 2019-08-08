package techcourse.myblog.service.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserEditRequest {
    @NotBlank(message = "이름을 작성해주세요!")
    @Pattern(regexp = "[^ !@#$%^&*(),.?\\\":{}|<>0-9]{2,10}",
            message = "이름은 2~10자로 제한하며 숫자나 특수문자가 포함될 수 없습니다!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
