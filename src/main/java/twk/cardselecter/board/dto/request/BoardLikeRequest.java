package twk.cardselecter.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardLikeRequest {
    @NotBlank
    private int seq;
    @NotBlank
    private String id;
}
