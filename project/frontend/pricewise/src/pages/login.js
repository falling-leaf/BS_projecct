import React from 'react';
import { Input, Button } from 'antd';
import logo from '../assets/images/logo.png'
import './login.css';

const Login = () => {

  const handleLogin = () => {
    window.location.href = '/search';
  };

  const handleRegister = () => {
    window.location.href = '/register';
  };

  const handleForgetPassword = () => {
    window.location.href = '/forget';
  };

  return (
    <div className = "Login">
      <header className = "Login-header">
        <div>
            <img 
            src={logo} 
            alt="logo"
            style={{ width: 150, height: 150, margin: '0 auto' }} />
            <h3>欢迎来到Price-wise!</h3>

            <p>登录Price-wise，开始您的购物之旅！</p>

            <Input 
            prefix = "用户名:" 
            size = "large" 
            style={{ width: 300 }} 
            placeholder="用户名" />
            <p></p>
            <div>
            <Input 
            prefix = "密码:" 
            size = "large" 
            style={{ width: 300, contentAlign: 'center' }} 
            placeholder="密码" />
            <p/>
            </div>

            <Button 
            onClick={handleLogin} 
            type="primary" 
            size="large" 
            style={{ width: 300 }}>登录</Button>
            <p></p>

            <Button 
            type = "dashed" 
            size = "large" 
            style = {{ width: 300 }} 
            onClick={handleForgetPassword}>忘记密码?</Button>

            <p></p>
            <Button 
            type = "dashed"
            size = "large"
            style = {{ width: 300 }}
            onClick={handleRegister}>注册新账户</Button>
        </div>
      </header>
    </div>
    );
};
  

export default Login;