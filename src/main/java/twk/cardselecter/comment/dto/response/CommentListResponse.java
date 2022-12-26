package twk.cardselecter.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.comment.entity.Comment;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class CommentListResponse {
    private List<Comment> commentList;
    private Integer pageCnt;
}
