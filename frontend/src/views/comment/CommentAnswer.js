import {useState} from "react";
import CommentWrite from "./CommentWrite";
import {useSelector} from "react-redux";

function CommentAnswer(props){
    const [answerComm, setAnswerComm] = useState(false);
    const comment = props.obj;
    const user = useSelector((state) => state.user.value);
    const id = user.id;

    const answerComment = () => {
        setAnswerComm(!answerComm);
    }
    return(
        <div id={"comment-answer-wrap"}>
            <div className={"button-zero text-middle btn"} onClick={answerComment}>
                답글달기
            </div>
            {
                answerComm ?
                    <CommentWrite seq={comment.boardSeq} pseq={comment.seq}/>
                    :
                    null
            }
        </div>
    )
}
export default CommentAnswer;