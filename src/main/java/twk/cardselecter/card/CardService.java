package twk.cardselecter.card;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import twk.cardselecter.card.dto.param.CardCustomListParam;
import twk.cardselecter.card.dto.param.CardListParam;
import twk.cardselecter.card.dto.reqeust.CardCustomCreateRequest;
import twk.cardselecter.card.dto.reqeust.CardCustomDeleteRequest;
import twk.cardselecter.card.dto.reqeust.CardCustomListRequest;
import twk.cardselecter.card.dto.reqeust.CardListRequest;
import twk.cardselecter.card.dto.response.CardCustomResultResponse;
import twk.cardselecter.card.dto.response.CardCustomListResponse;
import twk.cardselecter.card.dto.response.CardListResponse;
import twk.cardselecter.card.dto.response.CardResponse;
import twk.cardselecter.card.entity.Card;
import twk.cardselecter.card.entity.CardBenefit;
import twk.cardselecter.card.entity.CustomCard;
import twk.cardselecter.card.respository.CardRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class CardService {
    private final CardRepository repository;

    /**
     * 메인페이지 및 랭킹 카드 리스트 조회
     */
    public CardListResponse getCardList(CardListRequest req){
        CardListParam param = new CardListParam(req.getPage(), req.getOrderBy(), req.getChoice());
        param.setPageParam(req.getPage(), req.getItemCount());
        int pageCnt = repository.getCardCount(param);
        List<Card> cardList = repository.getCardSearchPageList(param);
        return new CardListResponse(cardList, pageCnt);
    }

    /**
     * 특정 카드 조회(카드 정보, 카드 혜택, 카드 선호도)
     */
    public CardResponse getCard(String no){
            Card card = repository.getCard(no);
            List<CardBenefit> cardBenefitList = repository.getCardBenefit(no);
            Map<Integer, Float> wantAge = wantAge(repository.getCardAge(no).mapScore());
            return new CardResponse(card, cardBenefitList, wantAge);
    }

    /**
     * 커스텀 카드 조회
     */
    public CardCustomListResponse getCustomCard(CardCustomListRequest req){
        int itemCount = 10;
        CardCustomListParam param = new CardCustomListParam(req.getPage(), req.getId());
        param.setPageParam(req.getPage(), itemCount);
        List<CustomCard> customCard = repository.getCustomCardList(param);
        int pageCnt = repository.getCustomCardCount(param);
        return new CardCustomListResponse(customCard, pageCnt);
    }

    /**
     * 커스텀 카드 제작 나중에 예외처리
     */
    public CardCustomResultResponse createCustomCard(MultipartFile file, CardCustomCreateRequest req){
        try {
            String fileName = uploadFile(file);
            System.out.println(fileName + "11");
            CustomCard customCard = req.toEntity(fileName);
            Integer customCardResult = repository.createCustomCard(customCard);
            System.out.println(customCardResult + "22");
            return new CardCustomResultResponse(customCardResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 커스텀 카드 삭제
     */
    public CardCustomResultResponse deleteCustomCard(CardCustomDeleteRequest req){
        try {
            CustomCard customCard = req.toEntity();
            Integer deleteCardResult = repository.deleteCustomCard(customCard);
            return new CardCustomResultResponse(deleteCardResult);
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 가장 먼저 나오는 것만 return 한다(최대값 중복시, 연령이 낮은 것 리턴).
     */
    private Map<Integer, Float> wantAge(Map<Integer, Float> cardScoreMap){
        float maxValue = Collections.max(cardScoreMap.values());
        for(Integer key : cardScoreMap.keySet()){
            if(cardScoreMap.get(key)==maxValue)
                return Map.of(key, maxValue);
        }
        return null;
    }

    /**
     * 커스텀 카드 이미지 서버에 업로드(DB에 저장x)
     */
    private String uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        System.out.println(filename + "333");
        if(filename != null) {
            String fileExtension = filename.substring(filename.lastIndexOf("."));
            String uploadFolder = "/Users/twkim/Documents/server/";
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = uuids[0];
            String saveFile = uploadFolder+uniqueName+".png";
            Path savePath = Paths.get(saveFile);
            file.transferTo(savePath);
            return uniqueName;
        }
        throw new IOException();
    }
}
