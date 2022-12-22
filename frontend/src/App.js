import './css/reset.css';
import "./css/component.css"
import Main from "./views/component/Main";
import {BrowserRouter} from "react-router-dom";
import Header from "./views/component/Header";
import Nav from "./views/component/Nav";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Header/>
            <Nav/>
            <Main/>
        </BrowserRouter>
    </div>
  );
}

export default App;
