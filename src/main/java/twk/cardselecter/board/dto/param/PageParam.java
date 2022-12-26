package twk.cardselecter.board.dto.param;

import lombok.Getter;
@Getter
public abstract class PageParam {
    private Integer pageStart;
    private Integer pageEnd;

    public void setPageParam(Integer page, Integer itemCount) {
        page -= 1;

        pageStart = page * itemCount + 1;
        pageEnd = (page + 1) * itemCount;
    }
}