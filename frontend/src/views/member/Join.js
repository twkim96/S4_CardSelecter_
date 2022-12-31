/* 회원가입 컴포넌트 */
import axios from "axios";
import {useState} from "react";
import {useNavigate} from "react-router";

function Join() {

    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [pwd, setPwd] = useState("");
    const [checkPwd, setCheckPwd] = useState("");
    const [email, setEmail] = useState("");

    const navigate = useNavigate();

    const changeId = (event) => {
        setId(event.target.value);
    }

    const changeName = (event) => {
        setName(event.target.value);
    }

    const changePwd = (event) => {
        setPwd(event.target.value);
    }

    const changeCheckPwd = (event) => {
        setCheckPwd(event.target.value);
    }

    const changeEmail = (event) => {
        setEmail(event.target.value);
    }

    /* 아이디 중복 체크 */
    const checkIdDuplicate = async () => {
        try {
            const resp = await axios.get("http://localhost:8818/user", {params: {id: id}});
            if (resp.status === 200)
                alert("사용 가능한 아이디입니다.")
        } catch (err) {
            const resp = err.response;
            if (resp.status === 400)
                alert(resp.data);
        }
    }

    /* 회원가입 */
    const join = async () => {
        const req = {
            id: id,
            name: name,
            pwd: pwd,
            email: email
        }
        try {
            const resp = await axios.post("http://localhost:8818/user/join", req)
            alert(resp.data.id + "님 회원가입을 축하드립니다.");
            navigate(-1);
        } catch (err) {
            const resp = err.response;
            if (resp.status === 400)
                alert(resp.data);
        }
    }


    return (
        <div id={"table-wrap"}>
            <ul className={"table"}>
                <li className={"text-title"}>
                    회원가입을 하러 오셨나요?
                </li>
                <li className={"text-big"}>
                    간단한 정보를 입력해주시면<br/>
                    회원가입이 단숨에 완료된답니다.
                </li>
                <li className={"empty-box"}>

                </li>
                <ul>
                    <li className="text-bigger">아이디</li>
                    <li>
                        <input type="text" value={id} onChange={changeId}
                               className={"inputBox-big text-big"}/>
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">이름</li>
                    <li>
                        <input type="text" value={name} onChange={changeName}
                                className={"inputBox-big text-big"}/>
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">비밀번호</li>
                    <li>
                        <input type="password" value={pwd} onChange={changePwd}
                               className={"inputBox-big text-big"}/>
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">비밀번호 확인</li>
                    <li>
                        <input type="password" value={checkPwd} onChange={changeCheckPwd}
                               className={"inputBox-big text-big"}/>
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">이메일</li>
                    <li>
                        <input type="text" value={email} onChange={changeEmail}
                               className={"inputBox-big text-big"}/>
                    </li>
                </ul>
                <li className="button-wrap">
                    <button className="buttonb" onClick={join}><i className="fas fa-user-plus"></i> 회원가입</button>
                </li>
            </ul>
        </div>
    );
}

export default Join;