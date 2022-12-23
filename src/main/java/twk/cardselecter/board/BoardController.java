package twk.cardselecter.board;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
