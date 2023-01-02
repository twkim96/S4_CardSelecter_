package twk.cardselecter.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import twk.cardselecter.comment.entity.Comment;

@Getter
@AllArgsConstructor
public class CommentPostRequest {
    @NotBlank(message = "로그인 상태를 확인해 주세요.")
    private String id;
    @NotBlank(message = "내용을 입력 해주세요.")
    private String content;
    private Integer boardSeq;
    private Integer emoticon;
    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .content(content)
                .boardSeq(boardSeq)
                .emoticon(emoticon)
                .build();
    }
}
