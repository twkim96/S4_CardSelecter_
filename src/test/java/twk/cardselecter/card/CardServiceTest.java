package twk.cardselecter.card;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import twk.cardselecter.card.dto.reqeust.CardCustomDeleteRequest;
import twk.cardselecter.card.dto.reqeust.CardCustomListRequest;
import twk.cardselecter.card.dto.reqeust.CardListRequest;
import twk.cardselecter.card.dto.response.CardCustomListResponse;
import twk.cardselecter.card.dto.response.CardCustomResultResponse;
import twk.cardselecter.card.dto.response.CardListResponse;
import twk.cardselecter.card.dto.response.CardResponse;
import twk.cardselecter.card.respository.CardRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired CardService service;
    @Autowired CardRepository repository;

    @Test
    void getCardList() {
        CardListResponse score = service.getCardList(
                new CardListRequest(1, 0, "score30", 5));
        System.out.println(score);
    }

    @Test
    void getCard() {
        CardResponse card = service.getCard("tw002");
        System.out.println(card);
    }

    @Test
    void getCustomCard() {
//        CardCustomListResponse customCard = service.getCustomCard(new CardCustomListRequest(1, "id"));
//        System.out.println(customCard);
    }

    @Test
    void createCustomCard() {
//        service.createCustomCard();
    }

    @Test
    void deleteCustomCard() {
        CardCustomResultResponse deleteCustomCard = service.deleteCustomCard(new CardCustomDeleteRequest("id", "tw0010123"));
        System.out.println(deleteCustomCard);
    }
}