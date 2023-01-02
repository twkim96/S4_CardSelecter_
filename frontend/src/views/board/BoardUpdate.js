import {useSelector} from "react-redux";
import {useLocation, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {CKEditor} from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import parse from "html-react-parser";

function BoardUpdate(){
    const user = useSelector((state) => state.user.value)
    const navigate = useNavigate();
    const location = useLocation();
    const { board } = location.state;
    const [title, setTitle] = useState(board.title);
    const [content, setContent] = useState(board.content);

    const changeTitle = (event) => {
        setTitle(event.target.value);
    }
    const changeContent = (event) => {
        setContent(event);
        console.log(content)
    }
    const updateBoard = async () => {
        const req = {
            id: user.id,
            title: title,
            content: content
        }
        try {
            const resp = await axios.patch(`http://localhost:8818/board/${board.seq}`, req, {headers: user.jwt})
            if(resp.data.result === 1) {
                alert("개시글을 성공적으로 수정했습니다.");
                navigate(`/board/detail/${board.seq}`);
            }
        } catch (err) {
            alert("게시글을 등록하는데 실패했습니다. \n 원인: " + err.response.data)
            navigate(`/board/detail/${board.seq}`);
        }
    }

    useEffect(() => {
        if (!user.id) {
            alert("로그인 한 사용자만 게시글을 작성할 수 있습니다.");
            navigate(-1);
        }
    }, [])

    return (
        <div id={"board-write-wrap"}>
            <img src="/images/banner.jpg" alt=""/>
            <div className={"text-box"}>
                <p className={"text-title"}>남을 상처입히지 않는, 멋진 글</p>
                <p className={"text-big"}>QnA 게시판은 멋진 분들만 계신답니다.</p>
            </div>
            <ul className={"table"}>
                <input type="text" className="form-control" value={title} onChange={changeTitle} size="50px" />
            </ul>
            <ul className={"table"}>
            </ul>
            <ul>
                <CKEditor
                    editor={ClassicEditor}
                    data={content}
                    onReady={editor => {
                        // console.log('Editor is ready to use!', editor);
                    }}
                    onChange={(event, editor) => {
                        const data = editor.getData();
                        changeContent(data)
                    }}
                    onBlur={(event, editor) => {
                        // console.log('Blur.', editor);
                    }}
                    onFocus={(event, editor) => {
                        // console.log('Focus.', editor);
                    }}
                />
            </ul>
            <ul className={"table"}>
                <div className="button-zero button-submit text-big" onClick={updateBoard}>입 력</div>
            </ul>
        </div>
    )
}
export default BoardUpdate;