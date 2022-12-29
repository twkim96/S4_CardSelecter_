package twk.cardselecter.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.entity.CustomCardToBoard;

@Getter
@AllArgsConstructor
public class BoardCreateRequest {
    @NotBlank(message = "로그인 상태를 확인해 주세요.")
    private String id;
    @NotBlank(message = "제목을 입력 해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력 해주세요.")
    private String content;
    private String filePath;
    public Board toEntity(){
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }

    public CustomCardToBoard toCardEntity(Integer seq){
        return CustomCardToBoard.builder()
                .filepath(filePath)
                .seq(seq)
                .build();
    }
}
