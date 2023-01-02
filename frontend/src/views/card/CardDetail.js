import {useEffect, useState} from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import CardMain from "../home/CardMain";
import {useSelector} from "react-redux";

function CardDetail() {
    const [card, setCard] = useState({});
    const [cardBene, setCardBene] = useState([{}, {}]);
    const {no} = useParams();
    const navigate = useNavigate();
    const fPath = `/images/${card.no}.jpg`;
    const [imgFile, setImgFile] = useState("");	//파일
    const user = useSelector((state) => state.user.value)
    const [isUpload, setIsUpload] = useState(false);
    const onChange = (e) => {
        setImgFile(e.target.files[0])
        console.log(imgFile)
    }
    const onSubmit = (e) => {
        e.preventDefault();
        const req = {
            id: user.id,
            no: card.no,
            name: card.name
        }
        const formData = new FormData();
        formData.append("img", imgFile);
        formData.append("req", new Blob([JSON.stringify(req)], {type:"application/json"}));
        //위 코드로 해결.
        console.log(e + formData);
        try {
            const resp = axios.post("http://localhost:8818/card/customCard", formData,{
                headers : {
                    "Content-Type": `multipart/form-data`,
                }});
            console.log(resp.data);
        } catch (err) {
            console.log(err)
        }
    }

    const getCardDetail = async () => {
        try {
            const resp = await axios.get(`http://localhost:8818/card/${no}`)
            setCard(resp.data.card);
            setCardBene(resp.data.cardBenefitList)
        } catch (err) {
            alert("카드 정보를 가져오는데 문제가 생겼습니다." + err)
            navigate(-1)
        }
    }
    const changeState = () => {
        setIsUpload(!isUpload);
    }

    useEffect(() => {
        getCardDetail();
    }, []);

    console.log(card.company)
    return (
        <div id="container-wrap">
            <div className="container">
                <div className="row">
                    <div className="img">
                        <img src={fPath} alt=""/>
                    </div>
                    <div className="data-box">
                        <h3>{card.cname}</h3>
                        <p className={"text-bigger"}>{card.company}</p>
                        <h4 className={"text-big"}>
                            {card.kind === 1 ? '국내전용' : 2 ? '비자카드' : 3 ? '마스터카드' : '유니온페이'}
                            ,
                            {card.inter === 1 ? '체크카드' : '신용카드'}</h4>
                        <hr/>
                        <h5>{card.info}</h5>
                        <hr/>
                    </div>
                </div>
                <div className={"banner"}>
                    <div className={"text-title"}>
                        혜택도 놓칠 수 없죠.
                    </div>
                    <div className={"text-big"}>
                        {cardBene[0].benefit}: {cardBene[0].amount}%할인<br/>
                        {cardBene[1].benefit}: {cardBene[1].amount}%할인
                    </div>
                </div>
                {card.company !== undefined ?
                    <CardMain name='같은 회사의 카드' orderBy={2} choice={card.company} itemCount={5} color={"#ffffff"}/>
                    :
                    null
                }
            </div>
            <div className={"go-board-wrap"}>
                <div className={"go-board"}>
                    <div className={"text-show"}>
                        <p className={"text-title text-center"}>잠깐, 구경만 하고 그냥 가시게요?</p>
                        <p className={"text-big text-center"}>
                            어떤 카드를 만들지 고민되는 당신을 위해 <br/>준비해놓은 멋진 기능이 여기 있습니다.<br/><br/>
                            내 맘대로 카드를 꾸며보는 기능이랍니다.<br/><br/>
                        </p>
                        <div onClick={changeState}>
                            <p className={"text-big text-center"}>[ 만들어 볼래요 ]</p>
                            <div className={"img-show"}>
                                <img src="/images/door.jpg" alt=""/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {(isUpload) ?
                <div className={"upload-img"}>
                    <form onSubmit={onSubmit}>
                        <label form={"file_up"}>업로드</label>
                        <input id="file_up" className={"button-zero"} type={'file'} accept={'image/jpg, image/png'} onChange={onChange}/>
                        <input className={"button-zero"} type="submit" value="Upload"/>
                    </form>
                </div>
                :
                <div className={"empty-area"}/>
            }
        </div>
    )
}

export default CardDetail;