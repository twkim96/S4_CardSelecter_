package twk.cardselecter.board;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
import twk.cardselecter.board.entity.CustomCardToBoard;
import twk.cardselecter.exception.DupKeyException;
import twk.cardselecter.board.repository.BoardRepository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

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
    public BoardPostResponse getBoard(Integer seq, String id) {
        if(!id.isEmpty()){
            try {
                Integer result = boardRepository.createBoardHistory(new BoardHistory(seq, id));
                Integer updateResult = boardRepository.updateBoardHistory(seq);
            } catch (DupKeyException | DataIntegrityViolationException e) {
//                throw new DupKeyException("한 번만 조회수가 추가됩니다.", HttpStatus.CONFLICT);
            } catch (Exception e){
//                System.out.println("오류오류");
            }
        }
        /*updateResult 사용해서 예외처리*/
        String customCardToBoard = boardRepository.getCustomCardToBoard(seq);
        return new BoardPostResponse(boardRepository.getBoard(seq), customCardToBoard);
    }

    /**
     * 글 추가 + 이미지 추가
     */
    public BoardCreateResponse createBoard(BoardCreateRequest req){
        Board board = req.toEntity();
        Integer result = boardRepository.createBoard(board);
        createCustomCardToBoard(req.getFilePath(), board.getSeq());
        return new BoardCreateResponse(board.getSeq());
    }

    private void createCustomCardToBoard(String filePath, Integer seq) {
        if(filePath == null)
            return;
        try {
            CustomCardToBoard customCard = CustomCardToBoard.builder()
                    .filePath(filePath).seq(seq).build();
            Integer customCardToBoardResult = boardRepository.createCustomCardToBoard(customCard);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    private void updateCustomCardToBoard(String filePath, Integer seq) {
        if(filePath == null) {
            Integer deleteResult = boardRepository.deleteCustomCardToBoard(seq);
            return;
        }
        try {
            CustomCardToBoard customCard = CustomCardToBoard.builder()
                    .filePath(filePath).seq(seq).build();
            Integer customCardToBoardResult = boardRepository.createCustomCardToBoard(customCard);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    /**
     * 답글 추가
     */
    public BoardCreateResponse createBoardAnswer(Integer parentSeq, BoardCreateRequest req){
        Board board = req.toEntity();
        Integer checkResult = boardRepository.updateBoardCheck(parentSeq);
        BoardAnswer boardAnswer = new BoardAnswer(board, checkResult, parentSeq);
        BoardStep boardStep = new BoardStep(checkResult, parentSeq);
        if (checkResult == 0) {
            Integer answerResult = boardRepository.createBoardAnswer(boardAnswer);
        } else {
            Integer updateStepResult = boardRepository.updateBoardStep(boardStep);
            Integer answerResult = boardRepository.createBoardAnswer(boardAnswer);
        }
        updateCustomCardToBoard(req.getFilePath(), board.getSeq());
        return new BoardCreateResponse(board.getSeq());
    }

    /**
     * 좋아요 추가(아이디 중복시 추가 X)
     */
    public BoardLikeResponse updateBoardLike(Integer seq, String id){
        if(!id.isEmpty()){
            try {
                Integer result = boardRepository.createBoardLike(new BoardLike(seq, id));
                System.out.println("result + " + result);
                if(result > 0) {
                    Integer updateResult = boardRepository.updateBoardLike(seq);
                    /*updateResult 사용해서 예외처리*/
                    return new BoardLikeResponse(result);
                }
            } catch (DupKeyException | DataIntegrityViolationException e){
                throw new DupKeyException("좋아요는 게시글당 한 번만 가능합니다.", HttpStatus.CONFLICT);
            }
        }
        return null;
    }

    /**
     * 글 수정
     */
    public BoardUpdateResponse updateBoard(Integer seq, BoardUpdateRequest req){
        Board board = Board.builder().seq(seq).id(req.getId())
                .content(req.getContent()).title(req.getTitle()).build();
        Integer updateResult = boardRepository.updateBoard(board);
        createCustomCardToBoard(req.getFilePath(), seq);
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
