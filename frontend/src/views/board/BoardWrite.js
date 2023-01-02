import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import axios from "axios";


function BoardWrite() {
    const user = useSelector((state) => state.user.value)
    const navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");

    const changeTitle = (event) => {
        setTitle(event.target.value);
    }
    const changeContent = (event) => {
        setContent(event);
        console.log(content)
    }
    const createBoard = async () => {
        const req = {
            id: user.id,
            title: title,
            content: content
        }
        try {
            const resp = await axios.post("http://localhost:8818/board", req, {headers: user.jwt})
            alert("개시글을 성공적으로 등록했습니다.");
            navigate(`/board/detail/${resp.data.seq}`);
        } catch (err) {
            alert("게시글을 등록하는데 실패했습니다. \n 원인: " + err.response.data)
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
                    config={{
                        placeholder: "<p>마음껏 질문해주세요!!</p>",
                    }}
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
                <div className="button-zero button-submit text-big" onClick={createBoard}>입 력</div>
            </ul>
        </div>
    )
}

export default BoardWrite;