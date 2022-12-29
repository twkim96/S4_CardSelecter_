package twk.cardselecter.card.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.card.entity.CustomCard;

@Getter
@AllArgsConstructor
public class CardCustomCreateRequest {
    private String id;
    private String no;
    private String name;

    public CustomCard toEntity(String filePath){
        return CustomCard.builder()
                .id(id)
                .no(no)
                .name(name)
                .filePath(filePath)
                .build();
    }
}
