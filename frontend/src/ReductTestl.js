import {useDispatch, useSelector} from 'react-redux';
import { login, logout } from './redux/user';
import {useEffect, useState} from "react";
import axios from "axios";

function ReductTestl() {
    const dispatch = useDispatch()
    const user = useSelector((state) => state.user.value)
    const doLogin = async () => {
        const resp = await axios.get('/test')
        console.log(resp)
        // dispatch(login({id: resp.data.id, pwd: resp.data.pwd, name: resp.data.name}))
        localStorage.setItem("id", resp.data.id)
        localStorage.setItem("pwd", resp.data.pwd)
        localStorage.setItem("name", resp.data.name)
        dispatch(login())
    }
    const doLogout = () => {
        console.log(user.id + " 1 " + localStorage.getItem("id"))
        localStorage.removeItem("id")
        localStorage.removeItem("pwd")
        localStorage.removeItem("name")
        // localStorage.removeItem("bbs_access_token")
        console.log(user.id + " 2 " + localStorage.getItem("id"))
        dispatch(logout())
        console.log(user.id + " 3 " + localStorage.getItem("id"))
    }

    useEffect(() => {

    })
    return (
        <div>
            <button onClick={() => {
                void doLogin()
            }}>Login</button>
            <button onClick={() => {
                doLogout()
            }}>Logout</button>
        </div>
    );
}

export default ReductTestl
