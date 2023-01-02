package twk.cardselecter.board.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import twk.cardselecter.board.dto.param.BoardAnswer;
import twk.cardselecter.board.dto.param.BoardListParam;
import twk.cardselecter.board.dto.param.BoardStep;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.entity.BoardHistory;
import twk.cardselecter.board.entity.BoardLike;
import twk.cardselecter.board.entity.CustomCardToBoard;
import twk.cardselecter.card.entity.CustomCard;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    List<Board> getBoardSearchPageList(BoardListParam param);
    Board getBoard(Integer seq);
    Integer getBoardCount(@Param("choice") String choice, @Param("search") String search);
    Integer getBoardAnswerCount(Integer parentSeq);
    Integer createBoard(Board board);

    Integer createBoardAnswer(BoardAnswer boardAnswer);
    Integer createBoardHistory(BoardHistory boardHistory);
    Integer createBoardLike(BoardLike boardLike);
    Integer updateBoard(Board board);
    Integer updateBoardCheck(Integer seq);
    Integer updateBoardStep(BoardStep boardStep);
    Integer updateBoardHistory(Integer seq);
    Integer updateBoardLike(Integer seq);
    Integer deleteBoard(Integer seq);
    String getCustomCardToBoard(Integer seq);
    Integer createCustomCardToBoard(CustomCardToBoard card);
    Integer deleteCustomCardToBoard(Integer seq);
    CustomCard findCustomCardNo(String filePath);

}
