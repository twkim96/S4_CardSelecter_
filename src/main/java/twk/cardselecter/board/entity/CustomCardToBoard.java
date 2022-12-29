package twk.cardselecter.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomCardToBoard {
    private String filepath;
    private int seq;
}
