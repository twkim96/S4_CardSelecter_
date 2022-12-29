package twk.cardselecter.card.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.card.entity.CustomCard;

@Getter
@AllArgsConstructor
public class CardCustomDeleteRequest {
    private String id;
    private String filePath;

    public CustomCard toEntity(){
        return CustomCard.builder()
                .id(id)
                .filePath(filePath)
                .build();
    }
}
