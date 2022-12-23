package twk.cardselecter.board.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardListParam extends PageParam {
    private String choice;
    private String search;
    private Integer page;
}
