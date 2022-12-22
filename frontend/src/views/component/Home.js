import Login from "../member/login";

function Home(){
    return(
        <div className={"homepage"}>
            <h1>홈페이지입니다.</h1>
            <Login/>
        </div>
    )
}

export default Home