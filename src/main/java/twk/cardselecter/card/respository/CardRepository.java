package twk.cardselecter.card.respository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import twk.cardselecter.board.entity.Board;
import twk.cardselecter.card.dto.param.CardCustomListParam;
import twk.cardselecter.card.dto.param.CardListParam;
import twk.cardselecter.card.entity.Card;
import twk.cardselecter.card.entity.CardBenefit;
import twk.cardselecter.card.entity.CardScore;
import twk.cardselecter.card.entity.CustomCard;

import java.util.List;

@Mapper
@Repository
public interface CardRepository {
    /*TODO 좋아요 클릭시 사용자 연령을 찾아서 연령별 좋아요 횟수 테이블에 추가*/
    List<Card> getCardSearchPageList(CardListParam param);
    Integer getCardCount(CardListParam param);
    Integer getCustomCardCount(CardCustomListParam param);
    List<CardBenefit> getCardBenefit(String no);
    Card getCard(String no);
    CardScore getCardAge(String no);
    List<CustomCard> getCustomCardList(CardCustomListParam param);
    void createCustomCard(CustomCard customCard);
    void deleteCustomCard(CustomCard customCard);
    List<Board> getBoardToCustomCard(String no);
}
