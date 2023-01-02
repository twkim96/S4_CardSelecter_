package twk.cardselecter.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.card.entity.CustomCard;

@Getter
@AllArgsConstructor
@ToString
public class BoardPostResponse {
    private Board board;
    private CustomCard customCard;
}
