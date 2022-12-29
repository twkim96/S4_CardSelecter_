package twk.cardselecter.card.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardListRequest {
    private Integer page;
    private Integer orderBy;
    private String choice;
}
