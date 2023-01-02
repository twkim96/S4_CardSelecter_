import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import axios from "axios";
import CustomCardList from "../card/CustomCardList";


function BoardWrite() {
    const user = useSelector((state) => state.user.value)
    const selectCard = useSelector((state) => state.selectCard.value)
    const navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [isShow, setIsShow] = useState(false);

    const changeTitle = (event) => {
        setTitle(event.target.value);
    }
    const changeContent = (event) => {
        setContent(event);
    }
    const createBoard = async () => {
        console.log(selectCard);
        console.log(user.jwt);
        const req = {
            id: user.id,
            title: title,
            content: content,
            no: selectCard.customCard,
            filePath: selectCard.customPath
        }
        console.log(req)
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
                <button onClick={()=>{setIsShow(!isShow)}}>내 커스텀 카드 보기</button>
            </ul>
            {
                isShow ?
                    <CustomCardList itemCount={5} select={true}/>
                    :
                    null
            }
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