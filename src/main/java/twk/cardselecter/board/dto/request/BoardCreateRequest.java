package twk.cardselecter.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardCreateRequest {
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
