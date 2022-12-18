import {useSelector} from "react-redux";
import {useState} from "react";
import axios from "axios";

function ReductTest(){
    const user = useSelector((state) => state.user.value)

    return(
        <div>
            <h1>Profile Page</h1>
            <p>id : {user.id}</p>
            <p>pwd : {user.pwd}</p>
            <p>name : {user.name}</p>
        </div>
    )
}

export default ReductTest