package twk.cardselecter.board;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twk.cardselecter.board.dto.request.BoardCreateRequest;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.request.BoardUpdateRequest;
import twk.cardselecter.board.dto.response.*;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * GET | board?choice={choice}&search={search}&page={page}
     * 게시글 목록
     */
    @GetMapping
    public ResponseEntity<BoardListResponse> getBoardList(@ModelAttribute BoardListRequest req) {
        BoardListResponse boardList = boardService.getBoardList(req);
        log.info("getBoardList {}", boardList);
        return ResponseEntity.ok(boardList);
    }

    /**
     * GET | board/{seq}?id={id}
     * 게시글 상세
     */
    @GetMapping("/{seq}")
    public ResponseEntity<BoardPostResponse> getBoard(@PathVariable Integer seq, @RequestParam String id) {
        BoardPostResponse boardInfo = boardService.getBoard(seq, id);
        log.info("getBoard {}", boardInfo);
        return ResponseEntity.ok(boardInfo);
    }

    /**
     * POST | board
     * 게시글 작성
     */
    @PostMapping
    public ResponseEntity<BoardCreateResponse> createBoard(@RequestBody BoardCreateRequest req) {
        BoardCreateResponse boardCreate = boardService.createBoard(req);
        log.info("createBoard {}", boardCreate);
        return ResponseEntity.ok(boardCreate);
    }

    /**
     * POST | /board/{parentSeq}/answer
     * 게시글 답글 작성
     */
    @PostMapping("/{parentSeq}/answer")
    public ResponseEntity<BoardCreateResponse> createBoardAnswer(@PathVariable Integer parentSeq, @RequestParam BoardCreateRequest req){
        BoardCreateResponse boardAnswerCreate = boardService.createBoardAnswer(parentSeq, req);
        log.info("createBoardAnswer {}", boardAnswerCreate);
        return ResponseEntity.ok(boardAnswerCreate);
    }

    /**
     * PATCH | /board/{seq}
     * 게시글 수정
     */
    @PatchMapping("/{seq}")
    public ResponseEntity<BoardUpdateResponse> updateBoard(@PathVariable Integer seq, @RequestBody BoardUpdateRequest req){
        BoardUpdateResponse boardUpdate = boardService.updateBoard(seq, req);
        log.info("updateBoard {}", boardUpdate);
        return ResponseEntity.ok(boardUpdate);
    }

    /**
     * DELETE | /board/{seq}
     * 게시글 삭제
     */
    @DeleteMapping("/{seq}")
    public ResponseEntity<BoardDeleteResponse> deleteBoard(@PathVariable Integer seq){
        BoardDeleteResponse boardDelete = boardService.deleteBoard(seq);
        log.info("deleteBoard {}", boardDelete);
        return ResponseEntity.ok(boardDelete);
    }
}


