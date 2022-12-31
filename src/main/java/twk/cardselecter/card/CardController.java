package twk.cardselecter.card;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import twk.cardselecter.card.dto.reqeust.*;
import twk.cardselecter.card.dto.response.CardCustomListResponse;
import twk.cardselecter.card.dto.response.CardCustomResultResponse;
import twk.cardselecter.card.dto.response.CardListResponse;
import twk.cardselecter.card.dto.response.CardResponse;

@Slf4j
@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {
    public final CardService service;

    /**
     * 카드 리스트 조회(메인 페이지)
     */
    @GetMapping
    public ResponseEntity<CardListResponse> getCardList(@ModelAttribute CardListRequest req){
        CardListResponse cardList = service.getCardList(req);
        log.info("getCardList {} ", cardList);
        return ResponseEntity.ok(cardList);
    }

    /**
     * 카드 리스트 조회(차트 페이지)
     */
    @GetMapping("/chart/{orderBy}")
    public ResponseEntity<CardListResponse> getCardChart(@PathVariable Integer orderBy,
                                                         @RequestParam String choice,
                                                         @RequestParam Integer page,
                                                         @RequestParam Integer itemCount) {
        CardListRequest cardListRequest = new CardListRequest(page, orderBy, choice, itemCount);
        CardListResponse cardList = service.getCardList(cardListRequest);
        log.info("cardList {}", cardList);
        return ResponseEntity.ok(cardList);
    }

    /**
     * 카드 정보 확인
     */
    @GetMapping("/{no}")
    public ResponseEntity<CardResponse> getCard(@PathVariable String no){
        CardResponse card = service.getCard(no);
        log.info("getCard {}", card);
        return ResponseEntity.ok(card);
    }

    /**
     * 커스텀 카드 조회(마이 페이지, 글쓰기 페이지)
     */
    @GetMapping("/customCard")
    public ResponseEntity<CardCustomListResponse> getCustomCardList(@ModelAttribute CardCustomListRequest req){
        CardCustomListResponse customCardList = service.getCustomCard(req);
        log.info("getCustomCardList {}", customCardList);
        return ResponseEntity.ok(customCardList);
    }

    /**
     * 커스텀 카드 생성(이미지 받아와서 생성)
     */
    @PostMapping("/customCard")
    public ResponseEntity<CardCustomResultResponse> createCustomCard(@RequestParam("img") MultipartFile file, @RequestBody CardCustomCreateRequest req){
        CardCustomResultResponse customCard = service.createCustomCard(file, req);
        log.info("createCustomCard {}", customCard);
        return ResponseEntity.ok(customCard);
    }

    /**
     * 커스텀 카드 삭제
     */
    @DeleteMapping("/customCard")
    public ResponseEntity<CardCustomResultResponse> deleteCustomCard(@RequestBody CardCustomDeleteRequest req){
        CardCustomResultResponse customCard = service.deleteCustomCard(req);
        log.info("deleteCustomCard {}", customCard);
        return ResponseEntity.ok(customCard);
    }
}
