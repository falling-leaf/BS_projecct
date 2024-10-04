// src/routes.js
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './pages/login.js';
import Register from './pages/register.js';
import Forget from './pages/forget.js';
import Menupage from './pages/menu.js';
import Searchpage from './pages/search.js';
import Detail from './pages/detail.js';

const AppRoutes = () => {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forget" element={<Forget />} />
          <Route path="/menu" element={<Menupage />} />
          <Route path="/search" element={<Searchpage />} />
          <Route path="/detail" element={<Detail />} />
          <Route path="*" element={<Login />} />
        </Routes>
      </Router>
    );
  };

export default AppRoutes;