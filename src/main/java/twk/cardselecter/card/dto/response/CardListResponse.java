package twk.cardselecter.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.card.entity.Card;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class CardListResponse {
    private List<Card> cardList;
    private Integer pageCnt;
}
