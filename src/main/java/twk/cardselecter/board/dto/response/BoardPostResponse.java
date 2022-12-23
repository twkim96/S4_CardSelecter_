package twk.cardselecter.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.board.entity.Board;

@Getter
@AllArgsConstructor
public class BoardPostResponse {
    private Board board;
}
