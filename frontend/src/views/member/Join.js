/* 회원가입 컴포넌트 */
import axios from "axios";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom"; //이부분 오류!! -dom 필수

function Join() {

    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [pwd, setPwd] = useState("");
    const [checkPwd, setCheckPwd] = useState("");
    const [email, setEmail] = useState("");
    const [msg, setMsg] = useState("");
    const [pwdMsg, setPwdMsg] = useState("");
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

    const checkId = (id) => {
        const regExp = /^[a-z]+[a-z0-9]{4,19}$/g;
        return regExp.test(id)
    }

    const checkPw = (pwd) => {
        const regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
        return regExp.test(pwd)
    }
    /* 아이디 중복 체크 */
    const checkIdDuplicate = async () => {
        try {
            if(checkId(id)) {
                const resp = await axios.get("http://localhost:8818/user", {params: {id: id}});
                if (resp.status === 200)
                    setMsg("사용 가능한 아이디입니다.")
            } else {
                setMsg("아이디를 확인해주세요(5~20자 내 영문).")
            }
        } catch (err) {
            const resp = err.response;
            if (resp.status === 400)
                setMsg("이미 있거나 삭제된 아이디입니다.")
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
        if(checkId(id) && checkPw(pwd)) {
            try {
                const resp = await axios.post("http://localhost:8818/user/join", req)
                alert(resp.data.id + "님 회원가입을 축하드립니다.");
                navigate(-1);
            } catch (err) {
                const resp = err.response;
                if (resp.status === 400)
                    alert(resp.data);
            }
        } else {
            alert("생성 규칙을 확인해 주세요.");
        }
    }

    const checkPass = () => {
        if(pwd === "")
            setPwdMsg("비밀번호를 입력해주세요.")
        else if(pwd === checkPwd)
            setPwdMsg("OK")
        else
            setPwdMsg("동일하지 않습니다. 다시 확인 해주세요.")
    }

    useEffect(() => {
        checkIdDuplicate();
        checkPass();
        checkPw(pwd);
    }, [id, checkPwd, pwd]);

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
                    <li>
                        {msg}
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">닉네임</li>
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
                    <li>
                        {checkPw ? "비밀번호를 확인해 주세요(8~16자, 영문, 숫자, 특수문자 포함)." : ""}
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">비밀번호 확인</li>
                    <li>
                        <input type="password" value={checkPwd} onChange={changeCheckPwd}
                               className={"inputBox-big text-big"}/>
                    </li>
                    <li>
                        {pwdMsg}
                    </li>
                </ul>
                <ul>
                    <li className="text-bigger">이메일</li>
                    <li>
                        <input type="text" value={email} onChange={changeEmail}
                               className={"inputBox-big text-big"}/>
                    </li>
                </ul>
                <ul>
                    <div className={"button-wrapper"}>
                        <button className="button-zero text-bigger" onClick={join}>
                            회원가입
                        </button>
                    </div>
                </ul>
            </ul>
        </div>
    );
}

export default Join;