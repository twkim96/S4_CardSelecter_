package twk.cardselecter.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.board.dto.request.BoardCreateRequest;
import twk.cardselecter.board.dto.response.BoardCreateResponse;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.repository.BoardRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    void getBoardList() {
    }

    @Test
    void getBoard() {
    }

    @Test
    void createBoard() {
        BoardCreateResponse boardCreate = boardService.createBoard(new BoardCreateRequest("id", "제목", "내용"));
        System.out.println(boardCreate + "hello");
    }

    @Test
    void createBoardAnswer() {
        BoardCreateResponse boardCreateAnswer = boardService.createBoardAnswer(
                5, new BoardCreateRequest ("id", "5답글", "내용"));
        System.out.println(boardCreateAnswer);
    }

    @Test
    void getBoardLike() {
    }

    @Test
    void updateBoard() {

    }

    @Test
    void deleteBoard() {
    }
}