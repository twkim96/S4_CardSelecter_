import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

function CommentWrite(props) {
    const dispatch = useDispatch()
    const user = useSelector((state) => state.user.value)
    const id = user.id
    const seq = props.seq;
    const pSeq = props.pseq;

    const navigate = useNavigate();

    const [content, setContent] = useState("");
    const [imo, setImo] = useState(false);
    const changeContent = (event) => {
        setContent(event.target.value);
    }
    const showEmoticon = () => {
        setImo(!imo);
    }
    const createComment = async () => {
        if (content === "") {
            return null
        }
        const url = (pSeq === undefined) ?
            `http://localhost:8818/comment` : `http://localhost:8818/comment/${pSeq}/answer`;
        const req = {
            id: id,
            emoticon: 0,
            content: content,
            boardSeq: seq
        }
        try {
            const resp = await axios.post(url, req, {
                params: {"boardSeq": seq},
                headers: user.jwt
            })
            if (resp.data.result != null) {
                navigate(0)
            }
        } catch (err) {
            alert(err.response.data)
        }
    }
    const createImoComment = async (emo) => {
        const req = {
            id: id,
            content: "",
            emoticon: emo,
            boardSeq: seq
        }
        const url = (pSeq === undefined) ?
            `http://localhost:8818/comment` : `http://localhost:8818/comment/${pSeq}/answer`;
        try {
            const resp = await axios.post(url, req, {
                params: {"boardSeq": seq},
                headers: user.jwt
            })
            setImo(false)
            if (resp.data.result != null) {
                navigate(0)
            }
        } catch (err) {
            alert(err.response.data)
        }
    }


    return (
        <div id={"comment-write-wrap"}>
            <ul className="comment-table">
                <li className="text-big">
                    {id}
                </li>
                <li>
                    <div className="button-zero text-middle" onClick={showEmoticon}>이모티콘</div>
                    <div className="button-zero text-middle" onClick={createComment}>댓글 추가</div>
                </li>
            </ul>
            {/* 하단 영역 (댓글 내용) */}
            {
                imo ?
                    <ul className={"comment-table"}>
                        <div className={"imt-show"} onClick={() => {
                            createImoComment(1);
                        }} >
                            <img src="/images/1.png" alt=""/>
                        </div>
                        <div className={"imt-show"} onClick={() => {
                            createImoComment(2);
                        }} >
                            <img src="/images/2.png" alt=""/>
                        </div>
                    </ul>
                    :
                    <ul className="comment-table">
                        <textarea className="text-middle" rows="4" value={content} onChange={changeContent}></textarea>
                    </ul>
            }
        </div>
    )
}

export default CommentWrite;