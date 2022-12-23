package twk.cardselecter.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardUpdateRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
