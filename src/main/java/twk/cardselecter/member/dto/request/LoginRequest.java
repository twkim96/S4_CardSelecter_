package twk.cardselecter.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;
    @NotBlank(message = "패스워드를 입력해주세요.")
    private String pwd;
}
