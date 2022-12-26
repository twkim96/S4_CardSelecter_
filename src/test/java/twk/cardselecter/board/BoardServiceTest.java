package twk.cardselecter.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.board.dto.request.BoardCreateRequest;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.response.BoardCreateResponse;
import twk.cardselecter.board.dto.response.BoardListResponse;
import twk.cardselecter.board.dto.response.BoardPostResponse;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.exception.DupKeyException;
import twk.cardselecter.board.repository.BoardRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    void getBoardList() {
        BoardListResponse boardList = boardService.getBoardList(new BoardListRequest("", "", 1));
        System.out.println(boardList);
    }

    @Test
    void getBoard() {
        try{
            BoardPostResponse boardPostResponse = boardService.getBoard(5, "id");
            System.out.println(boardPostResponse);
        } catch (DupKeyException e){
//            ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(e.getMessage(), e.getStatus());
//            System.out.println(stringResponseEntity);
            System.out.println("오류");
        }
    }

    @Test
    void createBoard() {
        BoardCreateResponse boardCreate = boardService.createBoard(new BoardCreateRequest("id", "제목", "내용"));
        System.out.println(boardCreate + "hello");
    }

    @Test
    void createBoardAnswer() {
        BoardCreateResponse boardCreateAnswer = boardService.createBoardAnswer(
                15, new BoardCreateRequest ("id", "15답글2", "내용"));
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