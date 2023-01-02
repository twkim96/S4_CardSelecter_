import {useDispatch, useSelector} from "react-redux";
import axios from "axios";
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {select} from "../../redux/select";
import {login} from "../../redux/user";

function CustomCardList(props){
    const user = useSelector((state) => state.user.value)
    const dispatch = useDispatch();
    const selectCard = useSelector((state) => state.selectCard.value)
    const [customCardList, setCustomCardList] = useState([]);
    const [totalCnt, setTotalCnt] = useState(0);
    const [page, setPage] = useState(1);
    const [data, setData] = useState(props);

    let maxPage = totalCnt % data.itemCount === 0 ?
        parseInt(totalCnt / data.itemCount) : parseInt(totalCnt / data.itemCount) + 1;
    const getCustomCardList = async (page) => {
        try {
            const resp = await axios.get("http://localhost:8818/card/customCard",
                {params: {"page": page, "id":user.id, "itemCount": data.itemCount}});
            setCustomCardList(resp.data.customCardList)
            setTotalCnt(resp.data.pageCnt);
        } catch (err) {
            alert("카드를 불러오는데 문제가 생겼습니다.")
        }
    }
    const clickLeft = () => {
        if (page === 1) {
            setPage(maxPage);
            getCustomCardList(page);
        } else {
            setPage(page - 1);
            getCustomCardList(page);
        }

    }
    const clickRight = () => {
        if (page === maxPage) {
            setPage(1);
            getCustomCardList(page);
        } else {
            setPage(page + 1);
            getCustomCardList(page);
        }
    }
    const onChange = (e) => {
        const card = e.target.value
        console.log(card)
        dispatch(select({customCard: card}))
    }
    useEffect(()=>{
        getCustomCardList(page)
    },[])

    return(
        <div>
            <div className="event-card">
                <div className={"text-title"}><h1>{props.name}</h1></div>
                <div className={"card-object"}>
                    <img src="/images/left-arrow.png" alt="" className={"left"}
                         onClick={clickLeft}/>
                    <img src="/images/right-chevron.png" alt="" className={"right"}
                         onClick={clickRight}/>
                    {
                        customCardList.map(function (card, idx) {
                            const path = "http://localhost:8818/upload/" + card.filePath + ".png";
                            return (
                                <div key={idx} className={"card-info"}>
                                    <div className={"img-wrap"}>
                                        <img src={path} alt="no-img"/>
                                    </div>
                                    <h3 className={"text-big text-center"}>{card.name}</h3>
                                    { data.select?
                                        <input type="radio" name="check" value={card.filePath} onChange={onChange}/>
                                        :
                                        null
                                    }
                                </div>
                            )
                        })
                    }
                </div>
            </div>
        </div>
    )
}
// <List obj={card} key={idx} cnt={idx + 1}/>
// function List(props) {
//     const card = props.obj;
//     const path = "http://localhost:8818/upload/" + card.filePath + ".png";
//     return (
//         <div className={"card-info"}>
//             <div className={"img-wrap"}>
//                 <img src={path} alt="no-img"/>
//             </div>
//             <h3 className={"text-big text-center"}>{card.name}</h3>
//             <input type="radio" onClick={onClick(card.name)}/>
//         </div>
//     )
// }
export default CustomCardList;