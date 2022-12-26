package twk.cardselecter.board.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BoardStep {
    Integer parentSeq;
    Integer checkResult;
}
