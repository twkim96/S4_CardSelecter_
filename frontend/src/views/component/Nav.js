import {Link} from "react-router-dom";

function Nav() {
    return (
        <div id="nav-container">
            <div className="nav">
                <ul className="nav-main">
                    <li>
                        <Link to={`/card/chart/0`} state={{
                            choice: "score20", itemCount:10}}><h1>연령별 순위</h1></Link>
                        <ul className="nav-show">
                            <li>
                                <Link to={`/card/chart/0`} state={{
                                    choice: "score20", itemCount:10}}>
                                    <p>20대 차트</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/0`} state={{
                                    choice: "score30", itemCount:10}}>
                                    <p>30대 차트</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/0`} state={{
                                    choice: "score30", itemCount:10}}>
                                    <p>40대 차트</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/0`} state={{
                                    choice: "score30", itemCount:10}}>
                                    <p>50대 차트</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/0`} state={{
                                    choice: "score30", itemCount:10}}>
                                    <p>60대 차트</p>
                                </Link>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <Link to={`/card/chart/1`} state={{
                            choice: "교통비 할인", itemCount:10}}>
                            <h1>혜택별 순위</h1>
                        </Link>
                        <ul className="nav-show">
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "교통비 할인", itemCount:10}}>
                                    <p>교통비 할인</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "교육비 할인", itemCount:10}}>
                                    <p>교육비 할인</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "쇼핑몰 할인", itemCount:10}}>
                                    <p>쇼핑몰 할인</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "통신비 할인", itemCount:10}}>
                                    <p>통신비 할인</p>
                                </Link>
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