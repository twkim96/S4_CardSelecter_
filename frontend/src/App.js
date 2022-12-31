import './css/reset.css';
import "./css/all.css"
import "./css/component.css"
import Main from "./views/component/Main";
import {BrowserRouter} from "react-router-dom";
import Header from "./views/component/Header";
import Nav from "./views/component/Nav";
import Footer from "./views/component/Footer";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Header/>
            <Nav/>
            <Main/>
            <Footer/>
        </BrowserRouter>
    </div>
  );
}

export default App;
