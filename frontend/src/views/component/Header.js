import UseDetectClose from "../hooks/UseDetectClose";
import {useSelector} from "react-redux";
import Login from "../member/Login";
import {Link, useNavigate} from "react-router-dom";

function Header() {
    const [isOpen, setIsOpen, dropDownRef] = UseDetectClose(false);
    const user = useSelector((state) => state.user.value)
    return (
        <header className="py-4">
            <div id="h-container">
                <div className="header">
                    <div>
                        <Link to={"/"}>
                            <h1>CARDSELECTER</h1>
                        </Link>
                    </div>
                    <div className="logo-desc">
                        <h3>카드를 고르는 현명한 방법</h3>
                    </div>
                    <div className="login-menu">
                        {user.id ?
                            <div className={"login-box"}>
                                <div>
                                    <Link className="nav-link" to="/logout">
                                        <img
                                            src="/images/baseline_logout_black_24dp.png"
                                            width="24"
                                            className={"img-click"}
                                            // onClick={() => setIsOpen(!isOpen)}
                                        />
                                        <p>로그아웃</p>
                                    </Link>
                                </div>
                            </div>

                            :
                            <div>
                                <div className={"dflex"}>
                                    <div className={"login-box"}>
                                        <Link className="dropdown-item" to="/join">
                                            <div>
                                                <img
                                                    src="/images/baseline_dashboard_black_24dp.png"
                                                    width="24"

                                                />
                                                <p>회원 가입</p>
                                            </div>
                                        </Link>
                                        <div onClick={() => setIsOpen(true)} ref={dropDownRef}>
                                            <img
                                                src="/images/baseline_lock_open_black_24dp.png"
                                                width="24"
                                                className={"img-click"}
                                                // onClick={() => setIsOpen(!isOpen)}
                                            />
                                            <p>로그인</p>
                                            <div className={isOpen ? "login-made active" : "login-made"}>
                                                <Login></Login>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        }
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;