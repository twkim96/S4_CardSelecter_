package twk.cardselecter.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.card.entity.Card;
import twk.cardselecter.card.entity.CardBenefit;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
public class CardResponse {
    private Card card;
    private List<CardBenefit> cardBenefitList;
    private Map<Integer, Float> wantAge;
}
