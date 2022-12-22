import { Routes, Route } from "react-router-dom";
import Home from "../component/Home";

function Router(){
    return(
        <Routes>
            <Routes>
                <Route path="/" element={<Home/>}/>
            </Routes>
        </Routes>
    )
}