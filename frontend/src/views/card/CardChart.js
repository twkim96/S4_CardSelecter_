import {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import "../../css/card.css"
import Pagination from "react-js-pagination";


function CardChart() {
    const [cardList, setCardList] = useState([]);
    const {orderBy} = useParams()
    const [fPath, setFPath] = useState('');
    const [fName, setFName] = useState('');
    const [page, setPage] = useState(1);
    const [totalCnt, setTotalCnt] = useState(0);
    const data = useLocation().state;
    const getCardList = async (p) => {
        const resp = await axios.get(
            `http://localhost:8818/card/chart/${orderBy}`, {
                params: {choice: data.choice, page: p, itemCount: data.itemCount}
            })
        setCardList(resp.data.cardList);
        setTotalCnt(resp.data.pageCnt);
        setFPath(`/images/${resp.data.cardList[2].no}.png`);
        setFName(resp.data.cardList[0].name);
    }

    useEffect(() => {
        getCardList(page)
    }, [data]);

    const changePage = (page) => {
        setPage(page);
        getCardList(page);
    }

    return (
        <div id="chart-view">
            <div className="container">
                <div className="row">
                    <div className="one">
                        <span>1</span>
                        <img src={fPath} alt=""/>
                        <h1>{fName}</h1>
                    </div>
                    <div className="zero">
                        <h1>카셀차트 TOP {page} ~ {page*10}</h1>
                    </div>
                    <ul className="box">
                        <li>
                            {
                                cardList.map(function (card, idx) {
                                    return (
                                        <List obj={card} key={idx} cnt={idx + 1} item={data.itemCount}/>
                                    )
                                })
                            }
                            <div className={"page"}>
                                <Pagination className="pagination"
                                            activePage={page}
                                            itemsCountPerPage={data.itemCount}
                                            totalItemsCount={totalCnt}
                                            pageRangeDisplayed={5}
                                            prevPageText={"‹"}
                                            nextPageText={"›"}
                                            onChange={changePage} />
                            </div>
                        </li>
                        <ul className={"empty-box"}>
                            <li>광고나 간단한 글이 들어갈 영역</li>
                            <li>광고나 간단한 글이 들어갈 영역</li>
                            <li>광고나 간단한 글이 들어갈 영역</li>
                        </ul>
                    </ul>

                </div>
            </div>
        </div>
    )
}

function List(props) {
    const card = props.obj;
    const path = "/images/" + card.no + ".png"
    return (
        <div>
            <ul className="fl-box">
                <li>
                    <h1>
                        {props.cnt}
                    </h1>
                </li>
                <li><img src={path} alt=""/></li>
                <li>
                    <div className="row-box">
                        <h2>
                            {card.name}
                        </h2>
                        <h3>
                            {card.company}
                        </h3>
                    </div>
                </li>
                <img src="/images/right.png" alt=""/>
            </ul>
        </div>
    )
}


export default CardChart;