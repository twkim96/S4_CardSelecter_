import { Routes, Route } from "react-router-dom";
import Home from "../component/Home";
import Logout from "../member/Logout";
import CardChart from "../card/CardChart";
import Join from "../member/Join";
import CardDetail from "../card/CardDetail";
import BoardList from "../board/BoardList";
import BoardWrite from "../board/BoardWrite";
import BoardDetail from "../board/BoardDetail";
import BoardUpdate from "../board/BoardUpdate";
import BoardAnswer from "../board/BoardAnswer";


function Router(){
    return(
        <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/join" element={<Join/>}/>
            <Route path="/logout" element={<Logout />}/>
            <Route path={"/card/chart/:orderBy"} element={<CardChart/>}/>
            <Route path="/card/detail/:no" element={<CardDetail/>}></Route>
            <Route path="/board/list" element={<BoardList />}></Route>
            <Route path="/board/write" element={<BoardWrite />}></Route>
            <Route path="/board/detail/:seq" element={<BoardDetail />}></Route>
            <Route path="/board/update" element={<BoardUpdate />}></Route>
            <Route path="/board/answer/:parentSeq" element={<BoardAnswer />}></Route>
        </Routes>
    )
}

export default Router;