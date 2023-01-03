import {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate, useParams} from "react-router-dom";
import Pagination from "react-js-pagination";
import {useSelector} from "react-redux";

function BoardList() {
    const [boardList, setBoardList] = useState([]);
    const [page, setPage] = useState(1);
    const [totalCnt, setTotalCnt] = useState(0);
    const user = useSelector((state) => state.user.value)

    let navigate = useNavigate();

    const changePage = (page) => {
        setPage(page);
        getBoardList(page);
    }
    const getBoardList = async (page) => {
        try {
            const resp = await axios.get(`http://localhost:8818/board/my`,
                {params: {"page": page, "id":user.id}});
            setBoardList(resp.data.boardList);
            setTotalCnt(resp.data.pageCnt);
        } catch (err) {
            alert("작성한 글을 불러오는데 문제가 생겼습니다.")
        }
    }

    useEffect(() => {
        getBoardList(1);
    }, []);

    return (
        <div>
            <div id={"board-list-wrap"}>
                <div className={"text-title my-home"}>
                    내가 쓴 글 / 만든 커스텀 카드
                </div>
                <div className="table-wrap">
                    {
                        boardList.map(function (board, idx) {
                            console.log(board, idx);
                            return (
                                <TableRow obj={board} key={idx} cnt={idx + 1}/>
                            )
                        })
                    }
                </div>
                <div className={"page-wrap"}>
                    <Pagination className="pagination"
                                activePage={page}
                                itemsCountPerPage={10}
                                totalItemsCount={totalCnt}
                                pageRangeDisplayed={5}
                                prevPageText={"‹"}
                                nextPageText={"›"}
                                onChange={changePage}/>
                </div>
            </div>
        </div>
    )
}

/* 글 목록 테이블 행 컴포넌트 */
function TableRow(props) {
    const board = props.obj;

    return (
        <ul className={"table"}>
            <li>{props.cnt}</li>

            <li className={"text-middle"}>
                <Link to={{pathname: `/board/detail/${board.seq}`}}> { /* 게시글 상세 링크 */}
                    {board.title}
                </Link>
            </li>
            <li className={"text-middle"}>{board.id}</li>
            <li className={"text-middle"}>{board.readCount}</li>
            <li className={"text-middle"}>{board.blike}</li>
            <li className={"text-middle"}>{board.createAt}</li>
        </ul>
    );
}

export default BoardList;