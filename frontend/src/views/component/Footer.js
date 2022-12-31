function Footer() {
    return (
        <footer>
            <div className={"footer-nav"}>
                <img src="/images/back-to-top.png" alt=""
                     onClick={() => {
                         window.scrollTo({
                             top: 0, behavior: 'smooth'
                         })
                     }}
                />
            </div>
            <div className={"footer-box-wrap"}>
                <div className={"footer-box text-middle text-line150"}>
                    Made by TWKim96<br/>
                    with Spring Boot + React<br/>
                    PC 로만 테스트하여 제작했습니다. 모바일은 제대로 적용 X<br/>
                    https://github.com/hellonayeon/bbs 를 참고하여 제작하였습니다.
                </div>
            </div>
        </footer>
    );
}

export default Footer;