import './css/reset.css';
import "./css/all.css"
import "./css/component.css"
import "./css/board.css"
import Main from "./views/component/Main";
import {BrowserRouter} from "react-router-dom";
import Header from "./views/component/Header";
import Nav from "./views/component/Nav";
import Footer from "./views/component/Footer";
import ScrollToTop from "./views/hooks/ScrollToTop";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <ScrollToTop />
            <Header/>
            <Nav/>
            <Main/>
            <Footer/>
        </BrowserRouter>
    </div>
  );
}

export default App;
