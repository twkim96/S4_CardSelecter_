import {useSelector} from "react-redux";
import {Link, useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {useEffect, useState} from "react";
import parse from 'html-react-parser';
import CommentList from "../comment/CommentList";
import CommentWrite from "../comment/CommentWrite";


function BoardDetail() {
    const user = useSelector((state) => state.user.value)
    const {seq} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] = useState({});
    const [customCard, setCustomCard] = useState({});

    const getBoardDetail = async () => {
        try {
            const resp = await axios(`http://localhost:8818/board/${seq}`, {
                params: {id: user.id ? user.id : ""}
            });
            setBoard(resp.data.board);
            setCustomCard(resp.data.customCard);
        } catch (err) {
            alert("게시글 정보를 읽어오는데 문제가 생겼습니다. msg:" + err)
        }
    }

    const deleteBoard = async () => {
        try {
            const resp = await axios.delete(`http://localhost:8818/board/${seq}`);
            if (resp.data.deleteResult === 1) {
                alert("게시글을 성공적으로 삭제했습니다.");
                navigate(-1);
            }
        } catch (err) {
            alert("게시글을 삭제하는데 문제가 생겼습니다. msg:" + err)
        }
    }

    const likeBoard = async () => {
        try {
            const req = {
                id: user.id
            }
            const resp = await axios.patch(
                `http://localhost:8818/board/${seq}/like`, req);
            if (resp.data.result === 1) {
                getBoardDetail();
            }
        } catch (err) {
            alert("게시글에 좋아요를 누르는데 문제가 생겼습니다. \n원인: " + err.response.data)
        }
    }
    useEffect(() => {
        getBoardDetail();
    }, [])

    const updateBoard = {
        seq: board.seq,
        id: board.id,
        title: board.title,
        content: board.content
    }

    const parentBoard = {
        id: board.id,
        title: board.title
    }
    const path = "http://localhost:8818/upload/" + customCard.filePath + ".png";
    return (
        <div id={"board-detail-wrap"}>
            <img src="/images/banner.jpg" alt=""/>
            <div className="table">
                <ul className={"table-flex table-flex-big text-big"}>
                    <li>{board.title}</li>
                    <li>{board.createAt}</li>
                </ul>
                <ul className={"table-flex table-flex-medium text-middle"}>
                    <li className={"text-big"}>{board.id}</li>
                    <li>조회수 <span>{board.readCount}</span></li>
                    <li>추천수 <span>{board.blike}</span></li>
                </ul>
                {
                    customCard.filePath !== "" ?
                        <ul className={"show-menu"}>
                            <img src={path} alt=""/>
                            <Link to={`/card/detail/${customCard.no}`}>
                                <p className={"text-middle"}>{user.id}님이 만들어낸 {customCard.name}의 변신</p>
                                <p><br/>카드 보러 가기</p>
                            </Link>
                        </ul>
                        :
                        null
                }
                <ul className={"table-contents text-middle"}>
                    <div className={"html-parse"} dangerouslySetInnerHTML={{__html: board.content}}>
                    </div>
                </ul>
                <ul className={"table-buttons text-big"}>
                    <li>
                        <Link to="/board/list">
                            글목록
                        </Link>
                    </li>
                    <li>
                        <Link to={`/board/answer/${board.seq}`}
                              state={{parentBoard: parentBoard}}>
                            <div className={"button-zero"}>답글</div>
                        </Link>
                    </li>
                    {
                        (user.id) ?
                            <div className={"text-big board-like"} onClick={likeBoard}>
                                <img src="/images/like.png" alt=""/>
                                <p>{board.blike}</p>
                            </div>
                            :
                            null
                    }
                    {
                        /* 자신이 작성한 게시글인 경우에만 수정 삭제 가능 */
                        (user.id === board.id) ?
                            <>
                                <li>
                                    <Link to="/board/update"
                                          state={{board: updateBoard}}>
                                        <div className={"button-zero"}>수정</div>
                                    </Link>
                                </li>
                                <li>
                                    <div className="button-zero" onClick={deleteBoard}>
                                        삭제
                                    </div>
                                </li>
                            </>
                            :
                            null
                    }
                </ul>

            </div>
            {
                (user.id) ? // 로그인한 사용자만 댓글 작성 가능
                    <CommentWrite seq={seq}/>
                    :
                    null
            }

            <CommentList seq={seq}/>
        </div>
    )
}

export default BoardDetail;