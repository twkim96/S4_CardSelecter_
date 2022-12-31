import { Routes, Route } from "react-router-dom";
import Home from "../component/Home";
import Logout from "../member/Logout";
import CardChart from "../card/CardChart";
import Join from "../member/Join";

function Router(){
    return(
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path={"/join"} element={<Join/>}/>
            <Route path="/logout" element={<Logout />}/>
            <Route path={"/card/chart/:orderBy"} element={<CardChart/>}/>
        </Routes>
    )
}

export default Router;