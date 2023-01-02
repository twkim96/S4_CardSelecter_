package twk.cardselecter.card.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardCustomListRequest {
    private Integer page;
    private String id;
    private Integer itemCount;
}
