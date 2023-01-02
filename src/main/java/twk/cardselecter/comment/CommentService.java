package twk.cardselecter.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import twk.cardselecter.comment.dto.param.CommentAnswer;
import twk.cardselecter.comment.dto.param.CommentListParam;
import twk.cardselecter.comment.dto.param.CommentStep;
import twk.cardselecter.comment.dto.request.CommentListRequest;
import twk.cardselecter.comment.dto.request.CommentPostRequest;
import twk.cardselecter.comment.dto.response.CommentDeleteResponse;
import twk.cardselecter.comment.dto.response.CommentListResponse;
import twk.cardselecter.comment.dto.response.CommentPostResponse;
import twk.cardselecter.comment.entity.Comment;
import twk.cardselecter.comment.repository.CommentRepository;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository commentRepository) {
        this.repository = commentRepository;
    }

    /**
     * 댓글 조회
     */
    public CommentListResponse getCommentList(CommentListRequest req){
        int itemCount = 10;
        CommentListParam param = new CommentListParam(req.getBoardSeq());
        param.setPageParam(req.getPage(), itemCount);
        List<Comment> commentList = repository.getCommentPageList(param);
        Integer pageCnt = repository.getCommentCount(req.getBoardSeq());
        return new CommentListResponse(commentList, pageCnt);
    }

    /**
     * 댓글 작성
     */
    public CommentPostResponse createComment(CommentPostRequest req){
        Comment comment = req.toEntity();
        Integer result = repository.createComment(comment);
        if (result < 1)
            return new CommentPostResponse(0);
        return new CommentPostResponse(comment.getSeq());
    }

    /**
     * 댓글 답변 작성
     */
    @Transactional
    public CommentPostResponse createCommentAnswer(Integer parentSeq, CommentPostRequest req){
        Comment comment = req.toEntity();
        Integer checkResult = repository.updateCommentCheck(parentSeq);
        CommentAnswer commentAnswer = new CommentAnswer(comment, checkResult, parentSeq);
        CommentStep commentStep = new CommentStep(checkResult, parentSeq);
        if (checkResult == 0){
            Integer answerResult = repository.createCommentAnswer(commentAnswer);
        } else {
            Integer updateStepResult = repository.updateCommentStep(commentStep);
            Integer answerResult = repository.createCommentAnswer(commentAnswer);
        }
        return new CommentPostResponse(comment.getSeq());
    }

    /**
     * 댓글 수정
     */
    public CommentPostResponse updateComment(CommentPostRequest req, Integer seq){
        Comment comment = Comment.builder()
                .seq(seq).id(req.getId())
                .content(req.getContent()).build();
        Integer updateResult = repository.updateComment(comment);
        return new CommentPostResponse(updateResult);
    }

    /**
     * 댓글 삭제
     */
    public CommentDeleteResponse deleteComment(Integer seq){
        Integer deleteResult = repository.deleteComment(seq);
        return new CommentDeleteResponse(deleteResult);
    }
}
