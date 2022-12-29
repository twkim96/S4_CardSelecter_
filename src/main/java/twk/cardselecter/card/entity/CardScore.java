package twk.cardselecter.card.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString
public class CardScore {
    private String no;
    private Float score20;
    private Float score30;
    private Float score40;
    private Float score50;
    private Float score60;
    public float avgScore(){
        return (score20 + score30 + score40 +
                score50 + score60) / 5;
    }
    public Map<Integer, Float> mapScore(){
        Map<Integer, Float> score = new HashMap<>();
        score.put(20, score20);
        score.put(30, score30);
        score.put(40, score40);
        score.put(50, score50);
        score.put(60, score60);
        return score;
    /*      return Map.of(
                "20대", cardScore.getScore20(),
                "30대", cardScore.getScore30(),
                "40대", cardScore.getScore20(),
                "50대", cardScore.getScore30(),
                "60대", cardScore.getScore30()
        );
    */
    }
}
