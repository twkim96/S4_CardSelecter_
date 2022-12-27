package twk.cardselecter.comment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Comment {
    private int seq;
    private String id;
    private int ref;
    private int step;
    private int depth;
    private String content;
    private String createAt;
    private int del;
    private int boardSeq;
    private int emoticon;
}
