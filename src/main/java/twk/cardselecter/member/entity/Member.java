package twk.cardselecter.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Member {
    private String id;
    private String pwd;
    private String name;
    private String email;
}
