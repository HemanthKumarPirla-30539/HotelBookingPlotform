import { useState } from 'react';
import axios from 'axios';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';

import Mem1 from './mem1';
import Mem2 from './mem2';
import Mem3 from './mem3';
import Mem4 from './mem4';
function App() {
  const [mode, setMode] = useState('home'); // home | signup | login | loggedin
  const [user, setUser] = useState({
    userName: '',
    userAdhar: '',
    location: '',
    bedShare: '',
    roomType: ''
  });

  const hotelList = ["Green Leaf", "Blue Orchid", "Skyview Inn", "Mountain Stay", "Ocean Breeze"];

  const handleChange = (e) => {
    setUser(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSignup = async () => {
    try {
      await axios.post('http://localhost:2828/hotel/insert', user);
      alert("Signup successful");
      setMode('home');
      setUser({
        userName: '',
        userAdhar: '',
        location: '',
        bedShare: '',
        roomType: ''
      });
    } catch {
      alert("Signup failed: User may already exist.");
    }
  };

  const handleLogin = async () => {
    try {
      await axios.post('http://localhost:2828/hotel/login', {
        userName: user.userName,
        userAdhar: user.userAdhar
      });
      alert("Login successful");
      setMode('loggedin');
    } catch {
      alert("Login failed");
    }
  };

  const bookHotel = async (hotelName) => {
    try {
      await axios.post('http://localhost:2828/hotel/book', {
        ...user,
        hotelName
      });
      alert(`Booking successful at ${hotelName}`);
    } catch {
      alert("Booking failed");
    }
  };

  return (
    <Router>
      <div style={{ padding: "2rem", fontFamily: "Arial" }}>
        <h1>🏨 Hotel Booking</h1>

       <nav>
  <Link to="/">Home</Link> |{" "}
  <Link to="/mem1">Mem1</Link> |{" "}
  <Link to="/mem2">Mem2</Link> |{" "}
  <Link to="/mem3">Mem3</Link> |{" "}
  <Link to="/mem4">Mem4</Link>
</nav>

        <hr />

        <Routes>
          <Route path="/" element={
            <>
              {mode === 'home' && (
                <>
                  <button onClick={() => setMode('signup')}>Signup</button>
                  <button onClick={() => setMode('login')} style={{ marginLeft: '1rem' }}>Login</button>
                </>
              )}

              {mode === 'signup' && (
                <>
                  <h2>Signup</h2>
                  <input name="userName" placeholder="User Name" onChange={handleChange} /><br /><br />
                  <input name="userAdhar" placeholder="User Adhar" onChange={handleChange} /><br /><br />
                  <button onClick={handleSignup}>Submit</button> &nbsp;
                  <button onClick={() => setMode('home')}>Back</button>
                </>
              )}

              {mode === 'login' && (
                <>
                  <h2>Login</h2>
                  <input name="userName" placeholder="User Name" onChange={handleChange} /><br /><br />
                  <input name="userAdhar" placeholder="User Adhar" onChange={handleChange} /><br /><br />
                  <input name="location" placeholder="Location" onChange={handleChange} /><br /><br />
                  <input name="bedShare" placeholder="Bed Share" onChange={handleChange} /><br /><br />
                  <input name="roomType" placeholder="Room Type" onChange={handleChange} /><br /><br />
                  <button onClick={handleLogin}>Login</button> &nbsp;
                  <button onClick={() => setMode('home')}>Back</button>
                </>
              )}

              {mode === 'loggedin' && (
                <>
                  <h2>You are logged in as <span style={{ color: "green" }}>{user.userName}</span></h2>
                  <div style={{ display: 'flex', flexWrap: 'wrap', gap: '1rem', marginTop: '2rem' }}>
                    {hotelList.map((hotel, index) => (
                      <div
                        key={index}
                        style={{
                          boxShadow: '0 4px 8px rgba(0,0,0,0.2)',
                          padding: '1rem',
                          borderRadius: '8px',
                          width: '200px',
                          background: '#f1f1f1'
                        }}
                      >
                        <h3>{hotel}</h3>
                        <button onClick={() => bookHotel(hotel)}>Book</button>
                      </div>
                    ))}
                  </div>
                </>
              )}
            </>
          } />
          <Route path="/mem1" element={<Mem1 />} />
<Route path="/mem2" element={<Mem2 />} />
<Route path="/mem3" element={<Mem3 />} />
<Route path="/mem4" element={<Mem4 />} />

        </Routes>
      </div>
    </Router>
  );
}

export default App;
