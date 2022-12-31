import {Link} from "react-router-dom";

function Nav() {
    return (
        <div id="nav-container">
            <div className="nav">
                <ul className="nav-main">
                    <li>
                        <Link className="dropdown-item" to='/'><h1>카셀차트</h1></Link>
                        <ul className="nav-show">
                            <li>
                                <Link to={`/card/chart/0`} state={{
                                    choice: "score20", itemCount:10}}>
                                    <p>카셀차트TOP10</p>
                                </Link>
                            </li>
                            <li>
                                <a href="#"><p>카드타입별순위</p></a>
                            </li>
                            <li>
                                <a href="#"><p>카드사별순위</p></a>
                            </li>
                            <li>
                                <a href="#"><p>연회비별순위</p></a>
                            </li>
                            <li>
                                <a href="#"><p>상황별혜택순위</p></a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><h1>카드사</h1></a>
                        <ul className="nav-show">
                            <li>
                                <a href="#">
                                    <p>협성카드</p>
                                </a>
                            </li>
                            <li>
                                <a href="#"><p>스시소카드</p></a>
                            </li>
                            <li>
                                <a href="#"><p>이클립스카드</p></a>
                            </li>
                            <li>
                                <a href="#"><p>톰캣카드</p></a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <h2>이벤트</h2>
                    </li>
                    <li>
                        <a href="#"><h1>게시판</h1></a>
                        <ul className={"nav-show"}>
                            <li>
                                <Link className="dropdown-item" to="/bbswrite"><p>글추가</p></Link>
                            </li>
                            <li>
                                <Link className="dropdown-item" to="/bbslist"><p>글목록</p></Link>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default Nav;