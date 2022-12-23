package twk.cardselecter.member.dto.request;

import jakarta.validation.constraints.NotBlank;
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
    private String name;
    @NotBlank
    private String email;

}
