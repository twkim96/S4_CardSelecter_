package twk.cardselecter.board.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import twk.cardselecter.board.entity.Board;

@Getter
@ToString
public class BoardAnswer {
    Board board;
    Integer seq;
    String id;
    String title;
    String content;
    Integer checkResult;
    Integer parentSeq;

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
