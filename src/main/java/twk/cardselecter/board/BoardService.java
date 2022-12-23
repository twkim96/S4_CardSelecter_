package twk.cardselecter.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.board.dto.page.BoardListParam;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.response.BoardListResponse;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.repository.BoardRepository;

import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public BoardListResponse getBoardList(BoardListRequest req){
        int itemCount = 5;
        BoardListParam param = new BoardListParam(req.getChoice(), req.getSearch(), req.getPage());
        param.setPageParam(req.getPage(), itemCount);
        List<Board> boardList = boardRepository.getBoardSearchPageList(param);
        int pageCnt = boardRepository.getBoardCount(req.getChoice(), req.getSearch());
        return new BoardListResponse(boardList, pageCnt);
    }

}
