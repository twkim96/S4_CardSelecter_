import { useDispatch } from 'react-redux';
import { login, logout } from './redux/user';
import {useState} from "react";
import axios from "axios";

function ReductTestl() {
    const dispatch = useDispatch()
    const getId = async () => {
        const resp = await axios.get('/test')
        console.log(resp)
        dispatch(login({id: resp.data.id, pwd: resp.data.pwd, name: resp.data.name}))
        localStorage.setItem("id", resp.data.id)
        localStorage.setItem("pwd", resp.data.pwd)
        localStorage.setItem("name", resp.data.name)
    }

    return (
        <div>
            <button onClick={() => {
                void getId()
            }}>Login</button>
            <button onClick={() => {
                dispatch(logout())
            }}>Logout</button>
        </div>
    );
}

export default ReductTestl
