import {useSelector} from "react-redux";
import {Link, useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {useEffect, useState} from "react";

function BoardDetail() {
    const user = useSelector((state) => state.user.value)
    const {seq} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] = useState({});

    const getBoardDetail = async () => {
        try {
            const resp = await axios(`http://localhost:8818/board/${seq}`, {
                params: {id: user.id ? user.id : ""}
            });
            setBoard(resp.data.board);
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

    useEffect(() => {
        getBoardDetail();
    }, [])

    return (

        <div className={"Bbs-wrap"}>

            <div className="my-3 d-flex justify-content-end">
                <Link className="btn btn-outline-secondary" to={{pathname: `/board/answer/${board.seq}`}}
                      state={{parentBbs: board}}><i className="fas fa-pen"></i> 답글쓰기</Link> &nbsp;

                {
                    /* 자신이 작성한 게시글인 경우에만 수정 삭제 가능 */
                    (localStorage.getItem("id") === board.id) ?
                        <>
                            <Link className="btn btn-outline-secondary" to="/bbsupdate" state={{bbs: board}}><i
                                className="fas fa-edit"></i> 수정</Link> &nbsp;
                            <button className="btn btn-outline-danger" onClick={deleteBoard}><i
                                className="fas fa-trash-alt"></i> 삭제
                            </button>
                        </>
                        :
                        null
                }

            </div>

            <table className="table table-striped">
                <tbody>
                <tr>
                    <th className="col-3">작성자</th>
                    <td>
                        <span>{board.id}</span>
                    </td>
                </tr>

                <tr>
                    <th>제목</th>
                    <td>
                        <span>{board.title}</span>
                    </td>
                </tr>

                <tr>
                    <th>작성일</th>
                    <td>
                        <span>{board.createdAt}</span>
                    </td>
                </tr>

                <tr>
                    <th>조회수</th>
                    <td>
                        <span>{board.readCount}</span>
                    </td>
                </tr>

                <tr>
                    <th>내용</th>
                    <td>
                        <div>
                            {board.content}
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>

            <div className="my-3 d-flex justify-content-center">
                <Link className="btn btn-outline-secondary" to="/bbslist"><i className="fas fa-list"></i> 글목록</Link>
            </div>
            <br/><br/>
        </div>
    )
}

export default BoardDetail;