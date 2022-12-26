package twk.cardselecter.comment.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CommentStep {
    private Integer checkResult;
    private Integer parentSeq;
}
