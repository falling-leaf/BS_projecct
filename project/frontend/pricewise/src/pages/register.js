import React from 'react';
import { Input, Button, Typography, message } from 'antd';
import axios from 'axios';
const { Link: AntLink } = Typography;

const Register = () => {
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [username, setUsername] = React.useState('');

  const handleUsername = (e) => {
    setUsername(e.target.value);
  }

  const handlePassword = (e) => {
    setPassword(e.target.value);
  }

  const handleEmail = (e) => {
    setEmail(e.target.value);
  }

  const handlerRegister = () => {
    console.log(email, password, username);
    axios.post('/user/register', null,{
      params: {
        account: username,
        password: password,
        email: email
      }
    })
   .then(res => {
      if (res.data === "register successfully") {
        console.log(res.data);
        message.success('注册成功，请返回登录页面进行登录');
        // window.location.href = '/login';
      } else {
        console.log(res.data);
        message.error(res.data);
      }
    })
   .catch(err => {
      console.log(err);
    });
    
  }

  return (
    <div className = "Login">
      <header className = "Login-header">
        <div>
          <h3>注册您的新账号，开启购物之旅！</h3>
          <Input 
            prefix = "用户名:" 
            size = "large" 
            style={{ width: 300 }} 
            onChange={handleUsername}
            placeholder="用户名不可少于6位" />
            <p />
            <Input.Password
            prefix = "密码:" 
            size = "large" 
            type="password" 
            style={{ width: 300 }} 
            onChange={handlePassword}
            placeholder="密码不可少于6位" />
            <p />
            <Input 
            prefix = "邮箱:" 
            size = "large" 
            style={{ width: 300 }} 
            onChange={handleEmail}
            onPressEnter={handlerRegister}
            placeholder="邮箱" />
            <p />
            <Button 
            type="primary" 
            size="large" 
            style={{ width: 300 }}
            onClick={handlerRegister}>注册</Button>
            <br />
            <AntLink href="/login">已有账号？点此登录</AntLink>
        </div>
      </header>
    </div>
    );
}
export default Register;