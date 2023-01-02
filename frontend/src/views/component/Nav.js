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
                            choice: "교통비", itemCount:10}}>
                            <h1>혜택별 카트</h1>
                        </Link>
                        <ul className="nav-show">
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "교통비", itemCount:10}}>
                                    <p>교통비 할인</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "교육비", itemCount:10}}>
                                    <p>교육비 할인</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "쇼핑몰", itemCount:10}}>
                                    <p>쇼핑몰 할인</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/1`} state={{
                                    choice: "통신비", itemCount:10}}>
                                    <p>통신비 할인</p>
                                </Link>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <Link to={`/card/chart/2`} state={{
                            choice: "삼성카드", itemCount:10}}>
                            <h1>회사별 카드</h1>
                        </Link>
                        <ul className="nav-show">
                            <li>
                                <Link to={`/card/chart/2`} state={{
                                    choice: "삼성카드", itemCount:10}}>
                                    <p>삼성 카드</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/2`} state={{
                                    choice: "국민카드", itemCount:10}}>
                                    <p>국민 카드</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/2`} state={{
                                    choice: "농협카드", itemCount:10}}>
                                    <p>농협 카드</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/2`} state={{
                                    choice: "신한카드", itemCount:10}}>
                                    <p>신한 카드</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/2`} state={{
                                    choice: "IBK카드", itemCount:10}}>
                                    <p>ibk 카드</p>
                                </Link>
                            </li>
                            <li>
                                <Link to={`/card/chart/2`} state={{
                                    choice: "롯데카드", itemCount:10}}>
                                    <p>롯데 카드</p>
                                </Link>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><h1>게시판</h1></a>
                        <ul className={"nav-show"}>
                            <li>
                                <Link className="dropdown-item" to="/board/write"><p>글추가</p></Link>
                            </li>
                            <li>
                                <Link className="dropdown-item" to="/board/list"><p>글목록</p></Link>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default Nav;