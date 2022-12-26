package twk.cardselecter.comment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import twk.cardselecter.comment.dto.param.CommentAnswer;
import twk.cardselecter.comment.dto.param.CommentListParam;
import twk.cardselecter.comment.dto.param.CommentStep;
import twk.cardselecter.comment.entity.Comment;

import java.util.List;

@Mapper
@Repository
public interface CommentRepository {
    List<Comment> getCommentPageList(CommentListParam commentListParam);
    Integer getCommentCount(Integer seq);
    Comment getCommentBySeq(Integer seq);
    Integer createComment(Comment comment);
    Integer createCommentAnswer(CommentAnswer commentAnswer);
    Integer updateComment(Comment comment);
    Integer updateCommentCheck(Integer seq);
    Integer updateCommentStep(CommentStep commentStep);
    Integer deleteComment(Integer seq);
}
