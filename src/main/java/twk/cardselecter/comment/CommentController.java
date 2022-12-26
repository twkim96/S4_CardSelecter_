package twk.cardselecter.comment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import twk.cardselecter.comment.dto.request.CommentListRequest;
import twk.cardselecter.comment.dto.request.CommentPostRequest;
import twk.cardselecter.comment.dto.response.CommentDeleteResponse;
import twk.cardselecter.comment.dto.response.CommentListResponse;
import twk.cardselecter.comment.dto.response.CommentPostResponse;

@Slf4j
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService service;

    /**
     * 댓글 목록 보기
     */
    @GetMapping
    public ResponseEntity<CommentListResponse> getCommentList(@ModelAttribute CommentListRequest req){
        CommentListResponse commentList = service.getCommentList(req);
        log.info("getCommentList {}", commentList);
        return ResponseEntity.ok(commentList);
    }

    /**
     * 댓글 작성
     */
    @PostMapping
    public ResponseEntity<CommentPostResponse> createComment(@RequestParam Integer boardSeq, @RequestBody CommentPostRequest req){
        CommentPostResponse commentPost = service.createComment(req);
        log.info("createComment {}", commentPost);
        return ResponseEntity.ok(commentPost);
    }

    /**
     *  답 댓글 작성
     */
    @PostMapping("/{parentSeq}/answer")
    public ResponseEntity<CommentPostResponse> createCommentAnswer(@PathVariable Integer parentSeq, @RequestBody CommentPostRequest req){
        CommentPostResponse commentAnswerCreate = service.createCommentAnswer(parentSeq, req);
        log.info("createCommentAnswer {}", commentAnswerCreate);
        return ResponseEntity.ok(commentAnswerCreate);
    }

    /**
     * 댓글 수정
     */
    @PatchMapping("/{seq}")
    public ResponseEntity<CommentPostResponse> updateComment(
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer seq,
            @RequestBody CommentPostRequest req){
        CommentPostResponse commentUpdate = service.updateComment(userDetails.getUsername(), seq, req);
        log.info("updateComment {}", commentUpdate);
        return ResponseEntity.ok(commentUpdate);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{seq}")
    public ResponseEntity<CommentDeleteResponse> deleteComment(@PathVariable Integer seq){
        CommentDeleteResponse commentDelete = service.deleteComment(seq);
        log.info("deleteComment {}", commentDelete);
        return ResponseEntity.ok(commentDelete);
    }
}
