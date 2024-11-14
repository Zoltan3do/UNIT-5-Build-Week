import './App.css';
import { BrowserRouter } from "react-router-dom"
import CustomLoginPage from './components/login/CustomLoginPage';
import "bootstrap/dist/css/bootstrap.min.css"

function App() {
  return (
    <BrowserRouter>
      <CustomLoginPage />

    </BrowserRouter>
  );
}

export default App;
