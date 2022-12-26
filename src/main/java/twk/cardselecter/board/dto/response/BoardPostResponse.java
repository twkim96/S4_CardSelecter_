package twk.cardselecter.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.board.entity.Board;

@Getter
@AllArgsConstructor
@ToString
public class BoardPostResponse {
    private Board board;
}
