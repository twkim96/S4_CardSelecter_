package twk.cardselecter.card.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.board.dto.param.PageParam;

@Getter
@AllArgsConstructor
public class CardCustomListParam extends PageParam {
    private Integer page;
    private String id;
}
