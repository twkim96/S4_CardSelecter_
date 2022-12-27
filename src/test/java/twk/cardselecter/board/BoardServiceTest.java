package twk.cardselecter.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.board.dto.request.BoardCreateRequest;
import twk.cardselecter.board.dto.request.BoardLikeRequest;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.request.BoardUpdateRequest;
import twk.cardselecter.board.dto.response.*;
import twk.cardselecter.exception.DupKeyException;
import twk.cardselecter.board.repository.BoardRepository;

@SpringBootTest
//@Transactional
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
            BoardPostResponse boardPostResponse = boardService.getBoard(20, "id");
            System.out.println(boardPostResponse);
        } catch (DupKeyException e){
            ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(e.getMessage(), e.getStatus());
            System.out.println(stringResponseEntity);

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
        try{
            BoardLikeResponse boardLike = boardService.updateBoardLike(20, "idf");
            System.out.println(boardLike + "1002354545");
        } catch (DupKeyException e){
            ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(e.getMessage(), e.getStatus());
            System.out.println(stringResponseEntity + "1002351235");
        }
    }

    @Test
    void updateBoard() {
        BoardUpdateResponse boardUpdate = boardService.updateBoard(20, new BoardUpdateRequest("kkjjkkjj", "19답글수정1", "내용2"));
        System.out.println(boardUpdate);
    }

    @Test
    void deleteBoard() {
    }
}