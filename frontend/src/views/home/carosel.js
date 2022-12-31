import React from 'react';
import {Carousel} from "antd";
const Carosel = () => {
    return (
        <div className={"Carousel-wrap"}>
            <div className={"Carousel-box"}>
                <Carousel autoplay>
                    <div>
                        <img src="/images/carosel1.jpeg" alt=""/>
                    </div>
                    <div>
                        <img src="/images/carosel2.jpeg" alt=""/>
                    </div>
                    <div>
                        <img src="/images/carosel3.jpeg" alt=""/>
                    </div>
                </Carousel>
            </div>
        </div>
    )
}
export default Carosel;
