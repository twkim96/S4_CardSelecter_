package twk.cardselecter.board.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import twk.cardselecter.board.entity.Board;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    List<Board> getBoardSearchPageList()
}
