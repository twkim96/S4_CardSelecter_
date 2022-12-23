package twk.cardselecter.board;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.response.BoardListResponse;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.entity.BoardHistory;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardController {

//    private final BoardService boardService;

//    @GetMapping
//    public ResponseEntity<BoardListResponse> getBoardList(@ModelAttribute BoardListRequest req){
//        return ResponseEntity.ok(boardService.getBbsList(req));
//    }
}
