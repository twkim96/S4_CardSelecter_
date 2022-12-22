import {useNavigate} from "react-router-dom";
import {useState} from "react";
import axios from "axios";

function login(){

    const navigate = useNavigate();

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");
    const changeId = (e) => {
        setId(e.target.value);
    }
    const changePwd = (e) => {
        setPwd(e.target.value);
    }

    const login = async () => {
        const req = {
            id: id,
            pwd: pwd
        }
        const resp = await axios.post("/user/login", req)
        try {
            alert(resp.data.id + "님, 성공적으로 로그인 되었습니다.");
            localStorage.setItem("bbs_access_token", resp.data.jwt);
            localStorage.setItem("id", resp.data.id);
            navigate("/");
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
                        <input type="password" value={pwd} onChange={changePwd} size="50px" />
                    </td>
                </tr>
                </tbody>
            </table><br />

            <div className="my-1 d-flex justify-content-center">
                <button className="btn btn-outline-secondary" onClick={login}><i className="fas fa-sign-in-alt"></i> 로그인</button>
            </div>

        </div>
    );
}

export default Login;