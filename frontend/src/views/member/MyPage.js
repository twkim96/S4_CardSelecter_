import CustomCardList from "../card/CustomCardList";
import React from "react";
import BoardListById from "../board/BoardListById";

function MyPage(){
    return(
        <div>
            <BoardListById/>
            <CustomCardList itemCount={5}/>
        </div>
    )
}
export default MyPage