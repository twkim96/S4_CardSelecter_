import Login from "../member/Login";

function Home(){
    return(
        <div className={"homepage"}>
            <h1>홈페이지입니다.</h1>

            <p>{localStorage.getItem("id")}</p>
        </div>
    )
}

export default Home