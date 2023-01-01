import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

function CardDetail() {
    const [card, setCard] = useState({});
    const {no} = useParams();
    const navigate = useNavigate();
    const fPath = `/images/${card.filePath}.png`;

    const getCardDetail = async () => {
        try {
            const resp = await axios.get(`http://localhost:8818/card/${no}`)
            setCard(resp.data.card);
        } catch (err) {
            alert("카드 정보를 가져오는데 문제가 생겼습니다." + err)
            navigate(-1)
        }
    }
    useEffect(() => {
        getCardDetail();
    }, []);
    return (
        <div id="container-wrap">
            <div className="container">
                <div className="row">
                    <div className="img">
                        <img src={fPath} alt=""/>
                    </div>
                    <div className="data-box">
                        <h3>{card.cname}</h3>
                        <p>{card.company}</p>
                        <h4>연회비 0원, {card.inter === 0 ? '체크카드' : '신용카드'}</h4>
                        <hr/>
                        <h5>{card.info}</h5>
                        <hr/>
                        <hr/>
                        <pre>
                            · 연체이자율 : 회원별/이용상품별 정상이자율 + 3%p, 최고 20%<br/><br/>

                            ※단, 연체발생시점에 정상이자율이 없는 경우 아래와 같이 적용함 -일시불 거래 연체 시 : 거래발생시점의 최소기간(2개월) 유이자<br/><br/>

                            - 무이자할부 거래 연체 시 : 거래발생시점의 동일한 할부 계약기간의 유이자 할부 수수료율 적용<br/><br/>

                            · 상환능력에 비해 신용카드 사용액이 과도할 경우 귀하의 개인신용평점이 하락할 수 있습니다.<br/><br/>

                            · 개인신용평점 하락 시 금융거래 관련된 불이익이 발생할 수 있습니다.<br/><br/>

                            · 일정기간 원리금을 연체할 경우, 모든 원리금을 변제할 의무가 발생할 수 있습니다.<br/><br/>



                            · 카드 신청 전 상품설명서 및 약관을 반드시 확인하시기 바랍니다.<br/><br/>

                            · 금융소비자는 해당상품 또는 서비스에 대하여 설명을 받을 권리가 있으며, 그 설명을 듣고 내용을 충분히 이해한 후 거래하시기 바랍니다.<br/><br/>

                            · 신용카드 발급이 부적정(개인신용평점 낮음, 연체금 보유 등)한 경우 카드 발급이 제한될 수 있습니다.<br/><br/>

                            · 카드이용대금과 이에 수반되는 모든 수수료를 지정된 대금 결제일에 상환합니다.<br/><br/>

                            · 여신금융협회 심의필 제 2022 - C1h - 01686호 ( 2022.03.15 ~ 2023.03.14 )<br/><br/>



                            · 고릴라디스트릭트는 KB국민카드의 신용카드 상품 판매 업무를 중개하고 있습니다.<br/><br/>

                            · 금융상품직접판매업자로부터 금융상품 계약체결권을 부여받지 아니한 금융상품판매대리﹒중개업자의 경우 금융상품계약을 체결할 수 없습니다.<br/><br/>

                            · 고릴라디스트릭트는 금융관계법률에 따라 (여신전문금융회사/신용카드사)와 제휴 계약을 체결한 금융상품 판매대리 중개업자입니다.<br/><br/>

                            · 고의 또는 과실로 금융소비자보호법을 위반하여 금융소비자에게 손해를 발생시킨 경우에는 그 손해를 배상할 책임이 있습니다.<br/><br/>

                            · 고릴라디스트릭트는 금융상품직접판매업자로부터 급부 수령에 관한 권한을 부여 받을 경우를 제외하고는 금융소비자로부터 급부를 수령할 권한이 없습니다.<br/><br/>

                            · 금융소비자가 제공한 개인(신용)정보 등은 금융상품직접판매업자가 보유 관리 하며, 고릴라디스트릭트는 기타 금융소비자보호법에서 요구하는 금융소비자 보호 또는 건전한 질서유지를 위한 내용을 준수하고 있습니다<br/><br/>

                            · 고릴라디스트릭트는 다수의 신용카드사를 대리하거나 중개합니다<br/><br/>

                            * 본 카드의 서비스 내용은 카드사 사정에 따라 사전 고지 후 변경 또는 중단될 수 있습니다.<br/><br/>
                            * 카드 사용 전 반드시 상품설명서와 약관을 읽어 보시기 바랍니다.<br/><br/>
                            * 카드고릴라는 소비자들에게 다양한 정보제공을 목적으로 심의번호가 미기재된 카드상품을 수집 및 노출할 수 있으며, 정보 업데이트에 최선을 다하고 있습니다.<br/><br/>
                        </pre>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CardDetail;