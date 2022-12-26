package twk.cardselecter.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentListRequest {
    private Integer boardSeq;
    private Integer page;
}
