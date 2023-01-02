import Login from "../member/Login";
import Carosel from "../home/carosel";
import "../../css/home.css"
import ShowBar from "../home/ShowBar";
import CardMain from "../home/CardMain";
import GoBoard from "../home/GoBoard";
import GoChart from "../home/GoChart";

function Home(){
    return(
        <div className={"homepage"}>
            <Carosel/>
            <ShowBar/>
            <GoBoard/>
            <CardMain name='20대에게 인기가 많은 카드' orderBy={0} choice={"score20"} itemCount={5} color={"#ffffff"}/>
            <CardMain name='대중교통러들에게 추천하는 카드' orderBy={1} choice={"교통비"} itemCount={5} color={"#f5f5f5"}/>
            <GoChart/>
            <CardMain name='학생을 키우는 부모님에게 추천하는 카드' orderBy={1} choice={"교육비"} itemCount={4} color={"#f5f5f5"}/>
            <CardMain name='50대에게 인기가 많은 카드' orderBy={0} choice={"score50"} itemCount={4} color={"#ffffff"}/>
            <p>{localStorage.getItem("id")}</p>
        </div>
    )
}

export default Home