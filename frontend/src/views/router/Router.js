import { Routes, Route } from "react-router-dom";
import Home from "../component/Home";
import Logout from "../member/Logout";

function Router(){
    return(
        <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/logout" element={<Logout />}/>

        </Routes>
    )
}

export default Router;