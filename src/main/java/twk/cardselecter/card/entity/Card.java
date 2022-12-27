package twk.cardselecter.card.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Card {
    private String no;
    private String name;
    private String company;
    private String info;
    private int kind;
    private int inter;
}
