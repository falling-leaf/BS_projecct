// src/routes.js
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './pages/login.js';
import Register from './pages/register.js';
import Forget from './pages/forget.js';

const AppRoutes = () => {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forget" element={<Forget />} />
        </Routes>
      </Router>
    );
  };

export default AppRoutes;