import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import CustomLogin from "./components/login/CustomLogin";
import RegisterPage from "./components/login/RegisterPage";
import CustomHomePage from "./components/homepage/CustomHomePage";
import MyProfile from "./components/MyProfile";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CustomLogin />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/homepage" element={<CustomHomePage />} />
        <Route path="/me" element={<MyProfile />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
