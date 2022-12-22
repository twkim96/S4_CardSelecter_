package twk.cardselecter.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    String id;
    String name;
    String jwt;
}
