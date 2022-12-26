package twk.cardselecter.comment.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import twk.cardselecter.board.dto.param.PageParam;

@Getter
@AllArgsConstructor
public class CommentListParam extends PageParam {
    private Integer boardSeq;
}
