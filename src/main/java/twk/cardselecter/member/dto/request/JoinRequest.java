package twk.cardselecter.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.member.entity.Member;

@Getter
@AllArgsConstructor
public class JoinRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String pwd;
    @NotBlank
    @Pattern(regexp = "^{4,30}$")
    private String name;
    @NotBlank
    private String email;

}
