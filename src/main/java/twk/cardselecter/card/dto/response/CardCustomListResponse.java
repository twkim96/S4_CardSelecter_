package twk.cardselecter.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.card.entity.CustomCard;

import java.util.List;
@Getter
@ToString
@AllArgsConstructor
public class CardCustomListResponse {
    private List<CustomCard> customCardList;
    private Integer pageCnt;
}
