import {useEffect, useState} from "react";
import {useNavigate} from "react-router";
import axios from "axios";
import {Link} from "react-router-dom";

function CardMain(props) {
    const [data, setData] = useState(props);
    const [cardList, setCardList] = useState([]);
    const [page, setPage] = useState(1);
    const [totalCnt, setTotalCnt] = useState(0);
    let maxPage = totalCnt % data.itemCount === 0 ?
        parseInt(totalCnt / data.itemCount) : parseInt(totalCnt / data.itemCount) + 1;
    const background = {backgroundColor: data.color}



    const getCardList = async (p, o, c, i) => {
        try {
            const resp = await axios.get("http://localhost:8818/card",
                {params: {"page": p, "orderBy": o, "choice": c, "itemCount": i}})
            setCardList(resp.data.cardList);
            setTotalCnt(resp.data.pageCnt);
        } catch (err) {
            alert("카드 정보를 불러오는데 문제가 발생했습니다. 네트워크를 확인해 주세요.")
        }
    }

    useEffect(() => {
        getCardList(1, data.orderBy, data.choice, data.itemCount);
    }, []);

    const clickLeft = () => {
        if (page === 1) {
            setPage(maxPage);
            getCardList(page, data.orderBy, data.choice, data.itemCount);
        } else {
            setPage(page - 1);
            getCardList(page, data.orderBy, data.choice, data.itemCount);
        }

    }
    const clickRight = () => {
        if (page === maxPage) {
            setPage(1);
            getCardList(page, data.orderBy, data.choice, data.itemCount);
        } else {
            setPage(page + 1);
            getCardList(page, data.orderBy, data.choice, data.itemCount);
        }
    }

    return (
        <div style={background}>
        <div className="event-card" >
                    <div className={"text-title"}><h1>{props.name}</h1></div>
                    <div className={"card-object"}>
                        <img src="/images/left-arrow.png" alt="" className={"left"}
                             onClick={clickLeft}/>
                        <img src="/images/right-chevron.png" alt="" className={"right"}
                             onClick={clickRight}/>
                        {
                            cardList.map(function (card, idx) {
                                return (
                                    <List obj={card} key={idx} cnt={idx + 1}/>
                                )
                            })
                        }
                    </div>
                </div>
        </div>
                )
            }

            function List(props) {
            const card = props.obj;
            const path = "/images/" + card.no + ".png"
            return (
            <div className={"card-info"}>
            <Link to={{pathname: `/carddetail/${card.no}`}}>
            <img src={path} alt=""/>
            <h3 className={"text-big text-center"}>{card.name}</h3>
            <p className={"text-middle text-center"}>{card.company}</p>
            </Link>
            </div>
            )
        }

            export default CardMain;