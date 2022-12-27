package twk.cardselecter.card.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CardBenefit {
    private String no;
    private String benefit;
    private float amount;
}
