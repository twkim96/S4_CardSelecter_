package twk.cardselecter.board.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BoardStep {
    private Integer checkResult;
    private Integer parentSeq;
}
