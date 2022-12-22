import {useEffect, useRef, useState} from "react";

const UseDetectClose = (initialState) => {
    const [isOpen, setIsOpen] = useState(initialState);
    const dropDownRef = useRef(null);


    useEffect(() => {
        const onClick = (e) => {
            if (dropDownRef.current !== null && !dropDownRef.current.contains(e.target)) {
                setIsOpen(!isOpen);
            }
        };

        if (isOpen) {
            window.addEventListener("click", onClick);
        }

        return () => {
            window.removeEventListener("click", onClick);
        };
    }, [isOpen]);
    return [isOpen, setIsOpen, dropDownRef];
};

export default UseDetectClose;