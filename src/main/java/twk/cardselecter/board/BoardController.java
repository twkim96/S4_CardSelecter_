package twk.cardselecter.board;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import twk.cardselecter.board.dto.request.BoardCreateRequest;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.request.BoardUpdateRequest;
import twk.cardselecter.board.dto.response.*;
import twk.cardselecter.exception.DupKeyException;
import twk.cardselecter.member.exception.MemberException;

import java.io.File;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService service;

    /**
     * GET | board?choice={choice}&search={search}&page={page}
     * 게시글 목록
     */
    @GetMapping
    public ResponseEntity<BoardListResponse> getBoardList(@ModelAttribute BoardListRequest req) {
        BoardListResponse boardList = service.getBoardList(req);
        log.info("getBoardList {}", boardList);
        System.out.println(new File("").getAbsolutePath() + "\\");
        System.out.println(System.getProperty("user.dir"));
        return ResponseEntity.ok(boardList);
    }

    /**
     * GET | board/{seq}?id={id}
     * 게시글 상세
     */
    @GetMapping("/{seq}")
    public ResponseEntity<BoardPostResponse> getBoard(@PathVariable Integer seq, @RequestParam String id) {
        BoardPostResponse boardInfo = service.getBoard(seq, id);
        log.info("getBoard {}", boardInfo);
        return ResponseEntity.ok(boardInfo);
    }

    /**
     * POST | board
     * 게시글 작성
     */
    @PostMapping
    public ResponseEntity<BoardCreateResponse> createBoard(@Valid @RequestBody BoardCreateRequest req) {
        BoardCreateResponse boardCreate = service.createBoard(req);
        log.info("createBoard {}", boardCreate);
        return ResponseEntity.ok(boardCreate);
    }

    /**
     * POST | /board/{parentSeq}/answer
     * 게시글 답글 작성
     */
    @PostMapping("/{parentSeq}/answer")
    public ResponseEntity<BoardCreateResponse> createBoardAnswer(@PathVariable Integer parentSeq, @Valid @RequestBody BoardCreateRequest req){
        BoardCreateResponse boardAnswerCreate = service.createBoardAnswer(parentSeq, req);
        log.info("createBoardAnswer {}", boardAnswerCreate);
        return ResponseEntity.ok(boardAnswerCreate);
    }

    /**
     * 좋아요 추가
     */
    @PatchMapping("/{seq}/like")
    public ResponseEntity<BoardLikeResponse> updateLike(@PathVariable Integer seq, @RequestBody String id){
        BoardLikeResponse boardLikeUpdate = service.updateBoardLike(seq, id);
        log.info("boardLikeUpdate {}", boardLikeUpdate);
        return ResponseEntity.ok(boardLikeUpdate);
    }
    /**
     * PATCH | /board/{seq}
     * 게시글 수정
     */
    @PatchMapping("/{seq}")
    public ResponseEntity<BoardUpdateResponse> updateBoard(@PathVariable Integer seq, @Valid @RequestBody BoardUpdateRequest req){
        BoardUpdateResponse boardUpdate = service.updateBoard(seq, req);
        log.info("updateBoard {}", boardUpdate);
        return ResponseEntity.ok(boardUpdate);
    }

    /**
     * DELETE | /board/{seq}
     * 게시글 삭제
     */
    @DeleteMapping("/{seq}")
    public ResponseEntity<BoardDeleteResponse> deleteBoard(@PathVariable Integer seq){
        BoardDeleteResponse boardDelete = service.deleteBoard(seq);
        log.info("deleteBoard {}", boardDelete);
        return ResponseEntity.ok(boardDelete);
    }
    /**
     * 요청 DTO 검증 예외처리 핸들러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("handleMethodArgumentNotValidException");
        BindingResult bs = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        bs.getFieldErrors().forEach(err -> {
            sb.append(String.format("[%s]: %s.\n입력된 값: %s",
                    err.getField(), err.getDefaultMessage(), err.getRejectedValue()));
        });

        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
    /**
     *  좋아요 관련 요청 예외처리 핸들러
     */
    @ExceptionHandler(DupKeyException.class)
    public ResponseEntity<?> handleUserException(DupKeyException e) {
        log.info("DupKeyException {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}


