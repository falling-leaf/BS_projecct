import React from 'react';
import { Input, Button, Typography } from 'antd';
const { Link: AntLink } = Typography;

const Register = () => {
  return (
    <div className = "Login">
      <header className = "Login-header">
        <div>
          <h3>注册您的新账号，开启购物之旅！</h3>
          <Input 
            prefix = "用户名:" 
            size = "large" 
            style={{ width: 300 }} 
            placeholder="用户名不可少于6位" />
            <p />
            <Input 
            prefix = "密码:" 
            size = "large" 
            type="password" 
            style={{ width: 300 }} 
            placeholder="密码不可少于6位" />
            <p />
            <Input 
            prefix = "邮箱:" 
            size = "large" 
            style={{ width: 300 }} 
            placeholder="邮箱" />
            <p />
            <Button type="primary" size="large" style={{ width: 300 }}>注册</Button>
            <br />
            <AntLink href="/login">已有账号？点此登录</AntLink>
        </div>
      </header>
    </div>
    );
}
export default Register;