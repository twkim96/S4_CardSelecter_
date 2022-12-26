package twk.cardselecter.board.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.board.entity.Board;

@Getter
@ToString
public class BoardAnswer {
    private Board board;
    private Integer seq;
    private String id;
    private String title;
    private String content;
    private Integer checkResult;
    private Integer parentSeq;

    public BoardAnswer(Board board, Integer checkResult, Integer parentSeq) {
        this.board = board;
        this.seq = board.getSeq();
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.checkResult = checkResult;
        this.parentSeq = parentSeq;
    }
}
