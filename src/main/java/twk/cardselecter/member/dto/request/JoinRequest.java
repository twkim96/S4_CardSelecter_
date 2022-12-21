package twk.cardselecter.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
