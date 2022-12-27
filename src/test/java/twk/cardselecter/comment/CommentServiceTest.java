package twk.cardselecter.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.comment.dto.request.CommentListRequest;
import twk.cardselecter.comment.dto.request.CommentPostRequest;
import twk.cardselecter.comment.dto.response.CommentListResponse;
import twk.cardselecter.comment.dto.response.CommentPostResponse;
import twk.cardselecter.comment.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class CommentServiceTest {

    @Autowired CommentService service;
    @Autowired CommentRepository repository;

    @Test
    void getCommentList() {
        CommentListResponse commentList = service.getCommentList(new CommentListRequest(19, 1));
        System.out.println(commentList);
    }

    @Test
    void createComment() {
        CommentPostResponse comment = service.createComment(new CommentPostRequest("id", "댓글내용", 19));
        System.out.println(comment);
    }

    @Test
    void createCommentAnswer() {
        CommentPostResponse commentPost = service.createCommentAnswer(3, new CommentPostRequest("id", "1댓글답글", 19));
        System.out.println(commentPost);
    }

    @Test
    void updateComment() {
        CommentPostResponse commentPost = service.updateComment("id", new CommentPostRequest("id", "내용수정", 3));
        System.out.println(commentPost);
    }

    @Test
    void deleteComment() {
    }
}