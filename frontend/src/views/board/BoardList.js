import {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import Pagination from "react-js-pagination";

function BoardList() {
    const [boardList, setBoardList] = useState([]);
    const [searchVal, setSearchVal] = useState("");
    const [choiceVal, setChoiceVal] = useState("");
    const [itemCount, setItemCount] = useState(20);
    const [page, setPage] = useState(1);
    const [totalCnt, setTotalCnt] = useState(0);
    let navigate = useNavigate();

    const changeChoice = (event) => {
        setChoiceVal(event.target.value);
    }
    const changeSearch = (event) => {
        setSearchVal(event.target.value);
    }
    const changeCount = (event) => {
        setItemCount(event.target.value);
    }
    const search = () => {
        console.log("[BbsList.js searchBtn()] choiceVal=" + choiceVal + ", searchVal=" + searchVal);

        navigate("/board/list");
        getBoardList(choiceVal, searchVal, 1, itemCount);
    }
    const changePage = (page) => {
        setPage(page);
        getBoardList(choiceVal, searchVal, page, itemCount);
    }
    const getBoardList = async (choice, search, page, itemCount) => {
        try {
            const resp = await axios.get("http://localhost:8818/board",
                {params: {"choice": choice, "search": search, "page": page, "itemCount": itemCount}});
            setBoardList(resp.data.boardList);
            setTotalCnt(resp.data.pageCnt);
            console.log(resp.data.boardList);
        } catch (err) {
            alert("게시판을 불러오는데 문제가 생겼습니다.")
        }
    }

    useEffect(() => {
        getBoardList("", "", 1, itemCount);
    }, [itemCount]);

    return (
        <div>
            <div id={"board-list-wrap"}>
                <img src="/images/banner.jpg" alt=""/>
                <div className="table-wrap">
                    <div className={"order"}>
                        <select className="custom-select" value={itemCount} onChange={changeCount}>
                            <option value="10">10개씩 보기</option>
                            <option value="20">20개씩 보기</option>
                            <option value="30">30개씩 보기</option>
                        </select>
                    </div>
                    <ul className={"table table-title"}>
                        <li className="text-middle text-center">번호</li>
                        <li className="text-middle text-center">제목</li>
                        <li className="text-middle text-center">작성자</li>
                        <li className={"text-middle text-center"}>조회수</li>
                        <li className={"text-middle text-center"}>추천수</li>
                        <li className={"text-middle text-center"}>작성시간</li>
                    </ul>
                    {
                        boardList.map(function (board, idx) {
                            console.log(board, idx);
                            return (
                                <TableRow obj={board} key={idx} cnt={idx + 1}/>
                            )
                        })
                    }
                </div>
                <div className={"search-wrap"}>
                    <div className="search">
                        <select className="custom-select" value={choiceVal} onChange={changeChoice}>
                            <option>검색 옵션 선택</option>
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                            <option value="writer">작성자</option>
                        </select>
                    </div>
                    <div>
                        <input type="text" className="form-control" placeholder="검색어" value={searchVal}
                               onChange={changeSearch} onKeyPress={(e)=>{
                            if(e.key==='Enter')
                                search();
                        }}/>
                    </div>
                    <div>
                        <button type="button" className="button-zero" onClick={search} >
                            <p className={"text-middle"}>검색</p>
                        </button>
                    </div>
                    <div className="button-wrapper">
                        <Link className="text-big" to="/board/write">
                            글쓰기
                        </Link>
                    </div>
                </div>
                <div className={"page-wrap"}>
                    <Pagination className="pagination"
                                activePage={page}
                                itemsCountPerPage={itemCount}
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
            {
                (board.del === 0) ?
                    // 삭제되지 않은 게시글
                    <>
                        <li className={"text-middle"}>
                            <Arrow depth={board.depth}></Arrow> { /* 답글 화살표 */}

                            <Link to={{pathname: `/board/detail/${board.seq}`}}> { /* 게시글 상세 링크 */}
                                {board.title}
                            </Link>
                        </li>
                        <li className={"text-middle"}>{board.id}</li>
                        <li className={"text-middle"}>{board.readCount}</li>
                        <li className={"text-middle"}>{board.blike}</li>
                        <li className={"text-middle"}>{board.createAt}</li>
                    </>
                    :
                    // 삭제된 게시글
                    <>
                    <div className={"text-delete"}>
                        <p className={"text-middle"}>⚠️ 이 글은 작성자에 의해 삭제됐습니다.</p>
                    </div>
                    </>
            }
        </ul>
    );
}

const tap = "\u00A0\u00A0\u00A0\u00A0";

function Arrow(props) {
    const depth = props.depth;

    if (depth === 0) {
        return null;
    }

    const taps = [];
    for (let i = 0; i < depth; i++) {
        taps.push(tap);
    }

    return (
        <>
            {taps}
            <img src="/images/letter-l.png" alt=""/>
        </>
    );
}

export default BoardList;