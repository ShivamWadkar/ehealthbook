import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './landing-page/LandingPage.tsx';
import SignUp from './sign-up/SignUp.tsx'
import AppAppBar from './landing-page/components/AppAppBar.tsx';
import SignInSide from './sign-in-side/SignInSide.tsx';
import Checkout from './checkout/Checkout.tsx'
import Dashboard from './dashboard/Dashboard.tsx'

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" 
            element={
              <>
                <AppAppBar />
                <LandingPage />
              </>
            }
           />

          <Route
            path="/sign-up"
            element={
              <>
                <AppAppBar />
                <SignUp />
              </>
            }
          />
          <Route
            path="/sign-in"
            element={
              <>
                <AppAppBar />
                <SignInSide />
              </>
            }
          />

          <Route path="/profile" element={<Checkout />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </Router>
  );
}
export default App;
