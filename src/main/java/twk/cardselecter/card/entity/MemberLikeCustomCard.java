package twk.cardselecter.card.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberLikeCustomCard {
    private String id;
    private String no;
    private String name;
    private String filePath;
}
