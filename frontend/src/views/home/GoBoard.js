import {Link} from "react-router-dom";

function GoBoard(){
    return(
        <div className={"go-board-wrap"}>
            <div className={"go-board"}>
                <div className={"text-show"}>
                    <p className={"text-title text-center"}>카드를 잘 모르는 당신에게</p>
                    <p className={"text-big text-center"}>
                        어떤 카드를 만들지 고민되는 당신을 위해 <br/>준비해놓은 멋진 문이 여기 있습니다.<br/><br/>
                        휘황찬란한 Q&A 게시판으로 들어가는 문이랍니다.<br/><br/>
                    </p>
                    <Link to={"/"}>
                        <p className={"text-big text-center"}>[ 들어갈래요 ]</p>
                        <div className={"img-show"}>
                            <img src="/images/door.jpg" alt=""/>
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    )
}
export default GoBoard;