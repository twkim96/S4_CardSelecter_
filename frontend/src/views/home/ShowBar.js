const ShowBar = () => {
    const images = ["Login", "ClassChart", "CustomCard", "Board"];
    return (
        <div className={"showBar-wrap"}>
            <div className={"contents-wrap"}>
                <div className={"text-title text-center"}>
                    <h1>카드 셀렉터의 기능</h1>
                </div>
                <div className={"contents"}>
                    {images.map((img) => (
                        <div key={img} className={"content"}>
                            <img src={`/images/${img}.jpg`} alt=""/>
                            <p className={"text-center text-big"}>{img} 기능</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}


export default ShowBar;