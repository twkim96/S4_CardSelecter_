import {useEffect, useState} from "react";
import axios from "axios";
import Pagination from "react-js-pagination";
import Comment from "./Comment";
import {useDispatch, useSelector} from "react-redux";
import CommentAnswer from "./CommentAnswer";

function CommentList(props){


    const seq = props.seq;


    // Paging
    const [page, setPage] = useState(1);
    const [totalCnt, setTotalCnt] = useState(0);
    const [commentList, setCommentList] = useState([]);

    const changePage = (page) => {
        setPage(page);
        getCommentList(page);
    }

    const getCommentList = async (page) => {
        console.log(page)
        try {
            const resp = await axios.get(`http://localhost:8818/comment`, { params: { "boardSeq": seq, "page": page } })
            setCommentList(resp.data.commentList);
            console.log(resp.data.commentList);
            setTotalCnt(resp.data.pageCnt);
        } catch (err) {
        }
    }

    useEffect(() => {
        getCommentList(1);
    }, []);

    return (
        <div className={"comment-list"}>
            <Pagination
                activePage={page}
                itemsCountPerPage={10}
                totalItemsCount={totalCnt}
                pageRangeDisplayed={5}
                prevPageText={"‹"}
                nextPageText={"›"}
                onChange={changePage} />
            {
                commentList.map(function (comment, idx) {
                    return (
                        <div key={idx}>
                            <Comment obj={comment} key={idx} />
                            <CommentAnswer obj={comment} key={idx}/>
                        </div>
                    );
                })
            }
        </div>

    );
}


export default CommentList;