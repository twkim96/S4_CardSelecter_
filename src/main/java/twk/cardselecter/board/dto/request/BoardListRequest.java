package twk.cardselecter.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardListRequest {
    private String choice;
    private String search;
    private Integer page;
    private Integer itemCount;
}
