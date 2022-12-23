package twk.cardselecter.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Board {
    private int seq;
    private String id;
    private int ref;
    private int step;
    private int depth;
    private String title;
    private String content;
    private String createAt;
    private int del;
    private int readCount;
    private int like;
}
