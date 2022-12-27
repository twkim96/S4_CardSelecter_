package twk.cardselecter.card.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CardScore {
    private String no;
    private float score20;
    private float score30;
    private float score40;
    private float score50;
    private float score60;

    public float avgScore(CardScore cardScore){
        return (this.score20 + this.score30 + this.score40 + this.score50 + this.score60) / 5;
    }
}
