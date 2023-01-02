import {useContext, useState} from "react";
import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import CommentWrite from "./CommentWrite";

function Comment(props) {
    const user = useSelector((state) => state.user.value)
    const comment = props.obj;
    const navigate = useNavigate();
    const [show, setShow] = useState(false);
    const [content, setContent] = useState(comment.content);

    const changeContent = (event) => {
        setContent(event.target.value);
    };

    /* 댓글 수정 */
    const updateComment = async () => {
        const req = {
            content: content
        };
        try {
            const resp = await axios.patch(`http://localhost:8818/comment/${comment.seq}`, req, {headers: user.jwt})
            alert("댓글을 성공적으로 수정했습니다.");
            navigate(0);
        } catch (err) {
            alert("댓글을 수정하는데 실패했습니다. \n 원인: " + err.response.data)
        }
        updateToggle();
    }

    /* 댓글 삭제 */
    const deleteComment = async () => {
        try {
            const resp = await axios.delete(`http://localhost:8818/comment/${comment.seq}`)
            if (resp.data.deleteResult === 1) {
                alert("댓글을 성공적으로 삭제했습니다.");
                navigate(0);
            }
        } catch (err) {
            alert("게시글을 삭제하는데 실패했습니다. \n 원인: " + err.response.data)
        }
    }


    function updateToggle() {
        setShow(show => !show)
    }

    // 삭제되지 않은 댓글의 경우
    if (comment.del === 0) {
        const fPath = `/images/${comment.emoticon}.png`;
        return (
            <div id={"comment-wrap"}>
                <div>
                    <ul className={"comment-table"}>
                        <Arrow depth={comment.depth} nope={false}></Arrow>
                        <li className={"text-impact"}>{comment.id}</li>
                        <li>{comment.createAt}</li>
                        {
                            /* 자신이 작성한 댓글인 경우에만 수정 삭제 가능 */
                            (user.id === comment.id) ?
                                <>
                                    <ul className={"buttons"}>
                                        <li className="button-zero" onClick={updateToggle}>수정</li>
                                        <li className="button-zero" onClick={deleteComment}>삭제</li>
                                    </ul>
                                </>
                                :
                                null
                        }
                    </ul>
                    {
                        /* 댓글 수정하는 경우 */
                        show ?
                            <ul className={"comment-table"}>
                                <li>
                                    {/* 하단 영역 (댓글 내용 + 댓글 내용 편집 창) */}
                                    <textarea className={"text-middle"} rows="5" value={content}
                                              onChange={changeContent}></textarea>
                                </li>
                                <li className="button-zero" onClick={updateComment}> 수정 완료</li>
                            </ul>
                            :
                            <ul className={"comment-table1"}>
                                <li>
                                    <Arrow depth={comment.depth} nope={true}></Arrow>
                                    {
                                        (comment.emoticon === 0) ?
                                            <div className="">
                                                <div className="">{content}</div>
                                            </div> :
                                            <div className="emo-wrap">
                                                <img src={fPath} alt="이미지가 없어요"/>
                                            </div>
                                    }
                                </li>
                            </ul>
                    }
                </div>
            </div>
        );
    }

    // 삭제된 댓글의 경우
    else {
        return (
            <div id={"comment-wrap"}>
                <ul className={"span-table"}>
                    <span>
                        ⚠️ 작성자에 의해 삭제된 댓글입니다.
                    </span>
                </ul>
            </div>
        );
    }
}

const tap = "\u00A0\u00A0\u00A0\u00A0";

function Arrow(props) {
    const depth = props.depth;
    const nope = props.nope;
    if (depth === 0) {
        return null;
    }

    const taps = [];
    if(!nope) {
        for (let i = 0; i < depth; i++) {
            taps.push(tap);
        }
    } else {
        for (let i = 0; i < depth-1; i++) {
            taps.push(tap);
        }
    }
    return (
        <>
            {taps}
            {!nope ?
                <img src="/images/letter-l.png" alt=""/>
                :
                null
            }

        </>
    );
}

export default Comment;