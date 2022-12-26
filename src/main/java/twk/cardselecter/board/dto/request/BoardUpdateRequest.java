package twk.cardselecter.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.board.entity.Board;

@Getter
@AllArgsConstructor
public class BoardUpdateRequest {
    @NotBlank(message = "로그인 상태를 확인해 주세요.")
    private String id;
    @NotBlank(message = "제목을 입력 해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력 해주세요.")
    private String content;
    public Board toEntity(){
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
