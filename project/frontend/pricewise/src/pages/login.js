import React from 'react';
import { Input, Button, Typography } from 'antd';
import { Link } from 'react-router-dom';

const Login = () => {
  return (
    <div>
        <h1>欢迎来到Price-wise!</h1>

        <p>登录Price-wise，开始您的购物之旅！</p>

        <Input 
        prefix = "用户名:" 
        size = "large" 
        style={{ width: 300 }} 
        placeholder="用户名" />
        <p></p>
        <Input 
        prefix = "密码:" 
        size = "large" 
        style={{ width: 300 }} 
        placeholder="密码" />
        <p></p>
        <Button type="primary" size="large" style={{ width: 300 }}>登录</Button>
        <p></p>
        <Link to="/register" component={Typography.Link}>没有账户？点我注册</Link>
        <div><Link to="/forget" component={Typography.Link}>忘记密码?</Link></div>
    </div>
    );
};
  

export default Login;