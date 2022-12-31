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
            <div className={"footer-box"}>
                푸터입니다.
            </div>
        </footer>
    );
}

export default Footer;