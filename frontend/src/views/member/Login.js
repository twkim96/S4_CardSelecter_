import {useNavigate} from "react-router-dom";
import {useState} from "react";
import axios from "axios";
import {useDispatch} from "react-redux";
import {login} from "../../redux/user";

function Login(){
    const dispatch = useDispatch();

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");
    const changeId = (e) => {
        setId(e.target.value);
    }
    const changePwd = (e) => {
        setPwd(e.target.value);
    }

    const logOn = async () => {
        const req = {
            id: id,
            pwd: pwd
        }
        console.log(id, pwd)
        try {
            const resp = await axios.post("http://localhost:8818/user/login", req)
            // const resp = await axios.all([axios.post("http://localhost:8818/user/login", req), axios.])
            console.log(resp)
            alert(resp.data.id + "님, 성공적으로 로그인 되었습니다.");
            // axios.defaults.headers.common['Authorization'] = `Bearer ${resp.data.jwt}`;
            localStorage.setItem("bbs_access_token", resp.data.jwt);
            localStorage.setItem("id", resp.data.id);
            localStorage.setItem("name", resp.data.name);

            dispatch(login({id: resp.data.id, name: resp.data.name,
                jwt: {"Authorization":`Bearer ${resp.data.jwt}`}}));
        } catch (err){
            console.log(err);
            alert(err.response.data);
        }
    }
    return (
        <div>
            <table className="table">
                <tbody>
                <tr>
                    <th className="col-3">아이디</th>
                    <td>
                        <input type="text" value={id} onChange={changeId} size="50px" />
                    </td>
                </tr>

                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input type="password" value={pwd} onChange={changePwd} size="50px" onKeyPress={(e)=>{
                            if(e.key==='Enter')
                                logOn();
                        }}/>
                    </td>
                </tr>
                </tbody>
            </table><br />

            <div className="my-1 d-flex justify-content-center">
                <button className="btn btn-outline-secondary" onClick={logOn}><i className="fas fa-sign-in-alt"></i> 로그인</button>
            </div>

        </div>
    );
}

export default Login;