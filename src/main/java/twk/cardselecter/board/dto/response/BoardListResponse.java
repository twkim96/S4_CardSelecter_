package twk.cardselecter.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.board.entity.Board;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class BoardListResponse {
    private List<Board> boardList;
    private Integer pageCnt;
}
