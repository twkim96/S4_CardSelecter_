import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "../../redux/user";
import {useEffect} from "react";

function Logout() {
    const dispatch = useDispatch()
    const user = useSelector((state) => state.user.value)
    const navigate = useNavigate();

    const logoff = () => {
        console.log("123");
        localStorage.removeItem("bbs_access_token");
        localStorage.removeItem("id");
        localStorage.removeItem("name");

        alert(user.name + "님, 성공적으로 로그아웃 됐습니다 🔒");
        dispatch(logout());
        navigate(-1);
    };

    useEffect(() => {
        logoff();
    }, []);

}

export default Logout;