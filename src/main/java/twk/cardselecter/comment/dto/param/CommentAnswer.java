package twk.cardselecter.comment.dto.param;

import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.comment.entity.Comment;

@Getter
@ToString
public class CommentAnswer {
    private final Comment comment;
    private final Integer seq;
    private final String id;
    private final String content;
    private final Integer checkResult;
    private final Integer parentSeq;
    private final Integer boardSeq;
    private final Integer emoticon;

    public CommentAnswer(Comment comment, Integer checkResult, Integer parentSeq) {
        this.comment = comment;
        this.checkResult = checkResult;
        this.parentSeq = parentSeq;
        this.seq = comment.getSeq();
        this.id = comment.getId();
        this.content = comment.getContent();
        this.boardSeq = comment.getBoardSeq();
        this.emoticon = comment.getEmoticon();
    }
}
