package twk.cardselecter.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String pwd;
    @NotBlank
    private String checkPwd;
    @NotBlank
    private String email;
}
