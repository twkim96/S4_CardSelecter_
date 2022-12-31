import {Link} from "react-router-dom";

function GoChart(){
    return(
        <div className={"go-chart-wrap"}>
            <div className={"go-board"}>
                <div className={"text-show"}>
                    <p className={"text-title text-center"}>철수 엄마가 만든 카드 이쁘던데</p>
                    <p className={"text-big text-center"}>
                        디자인적으로 이쁜 카드를 만들고 싶은 당신을 위해 <br/>준비해놓은 멋진 썰매가 여기 있습니다.<br/><br/>
                        썰매를 타고 가서 카드를 고르고 원하는 이미지를 넣을 수 있답니다.<br/><br/>
                    </p>
                    <Link to={"/"}>
                        <p className={"text-big text-center"}>[ 들어갈래요 ]</p>
                        <div className={"img-show"}>
                            <img src="/images/dog.jpeg" alt=""/>
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    )
}
export default GoChart;