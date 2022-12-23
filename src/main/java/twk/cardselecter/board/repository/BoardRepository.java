package twk.cardselecter.board.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import twk.cardselecter.board.dto.page.BoardListParam;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.entity.BoardHistory;
import twk.cardselecter.board.entity.BoardLike;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    List<Board> getBoardSearchPageList(BoardListParam param);
    Board getBoard(Integer seq);
    Integer getBoardCount(String choice, String search);
    Integer getBoardAnswerCount(Integer parentSeq);
    Integer createBoard(Board board);
    Integer createBoardAnswer(Board board);
    Integer createBoardHistory(BoardHistory boardHistory);
    Integer createBoardLike(BoardLike boardLike);
    Integer updateBoard(Board board);
    Integer updateBoardStep(Integer parentSeq);
    Integer updateBoardHistory(Integer seq);
    Integer updateBoardLike(Integer seq);
    Integer deleteBoard(Integer seq);
}
