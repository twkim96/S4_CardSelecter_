package twk.cardselecter.board.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardListIdParam extends PageParam {
    private Integer page;
    private String id;
}