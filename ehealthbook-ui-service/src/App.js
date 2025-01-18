import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './landing-page/LandingPage.tsx';
import SignUp from './sign-up/SignUp.tsx'
import AppAppBar from './landing-page/components/AppAppBar.tsx';
import SignInSide from './sign-in-side/SignInSide.tsx';

function App() {
  return (
    <Router>
      <div className="App">
        <AppAppBar />
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/sign-up" element={<SignUp />} />
          <Route path="/sign-in" element={<SignInSide />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
