package twk.cardselecter.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twk.cardselecter.board.dto.param.BoardAnswer;
import twk.cardselecter.board.dto.param.BoardListParam;
import twk.cardselecter.board.dto.param.BoardStep;
import twk.cardselecter.board.dto.request.BoardCreateRequest;
import twk.cardselecter.board.dto.request.BoardLikeRequest;
import twk.cardselecter.board.dto.request.BoardListRequest;
import twk.cardselecter.board.dto.request.BoardUpdateRequest;
import twk.cardselecter.board.dto.response.*;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.board.entity.BoardHistory;
import twk.cardselecter.board.entity.BoardLike;
import twk.cardselecter.board.repository.BoardRepository;

import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 게시글 조회
     */
    public BoardListResponse getBoardList(BoardListRequest req){
        int itemCount = 5;
        BoardListParam param = new BoardListParam(req.getChoice(), req.getSearch(), req.getPage());
        param.setPageParam(req.getPage(), itemCount);
        List<Board> boardList = boardRepository.getBoardSearchPageList(param);
        int pageCnt = boardRepository.getBoardCount(req.getChoice(), req.getSearch());
        return new BoardListResponse(boardList, pageCnt);
    }

    /**
     *  특정글 조회 + 조회수 수정
     */
    public BoardPostResponse getBoard(Integer seq, String id){
        if(!id.isEmpty()){
            Integer result = boardRepository.createBoardHistory(new BoardHistory(seq, id));
            if (result >= 1) {
                Integer updateResult = boardRepository.updateBoardHistory(seq);
            }
        }
        /*updateResult 사용해서 예외처리*/
        return new BoardPostResponse(boardRepository.getBoard(seq));
    }

    /**
     * 글 추가
     */
    public BoardCreateResponse createBoard(BoardCreateRequest req){
        Board board = req.toEntity();
        Integer result = boardRepository.createBoard(board);
        if (result < 1)
            return new BoardCreateResponse(0);
        return new BoardCreateResponse(board.getSeq());
    }

    /**
     * 답글 추가
     */
    public BoardCreateResponse createBoardAnswer(Integer parentSeq, BoardCreateRequest req){
        Board board = req.toEntity();
        System.out.println("check0"+board);
        Integer checkResult = boardRepository.updateBoardCheck(parentSeq);
        System.out.println("check1"+checkResult);
        BoardAnswer boardAnswer = new BoardAnswer(board, checkResult, parentSeq);
        System.out.println("check2"+boardAnswer);
        BoardStep boardStep = new BoardStep(parentSeq, checkResult);
        System.out.println("check3"+boardStep);
        if (checkResult == 0) {
            Integer answerResult = boardRepository.createBoardAnswer(boardAnswer);
            System.out.println("check4"+answerResult);
        } else {
            Integer updateStepResult = boardRepository.updateBoardStep(boardStep);
            System.out.println("check5"+updateStepResult);
            Integer answerResult = boardRepository.createBoardAnswer(boardAnswer);
            System.out.println("check6"+answerResult);
        }
        return new BoardCreateResponse(board.getSeq());
    }

    /**
     * 좋아요 추가(아이디 중복시 추가 X)
     */
    public BoardLikeResponse getBoardLike(BoardLikeRequest req){
        Integer result = 0;
        Integer createResult = boardRepository.createBoardLike(
                new BoardLike(req.getSeq(), req.getId()));
        if(createResult > 0){
            result = boardRepository.updateBoardLike(req.getSeq());
        }
        return new BoardLikeResponse(result);
    }

    /**
     * 글 수정
     */
    public BoardUpdateResponse updateBoard(Integer seq, BoardUpdateRequest req){
        Board board = req.toEntity();
        Integer updateResult = boardRepository.updateBoard(board);
        return new BoardUpdateResponse(updateResult);
    }

    /**
     * 글 삭제
     */
    public BoardDeleteResponse deleteBoard(Integer seq){
        Integer deleteResult = boardRepository.deleteBoard(seq);
        return new BoardDeleteResponse(deleteResult);
    }
}
