import React from 'react';
import { Input, Button } from 'antd';

const Forget = () => {
  const [email, setEmail] = React.useState('');
  const [username, setUsername] = React.useState('');
  const [code, setCode] = React.useState('');
  const [password, setPassword] = React.useState('');

  const handleSendCode = () => {
    console.log(email, username);
  }

  const handleSubmit = () => {
    console.log(email, username, code, password);
  }

  const handleBack = () => {
    window.location.href = '/login';
  }


  return (
    <div className = "Login">
      <header className = "Login-header">
        <div>
          <h2>忘记密码</h2>
          <h3>通过邮箱验证码重置密码！</h3>
          <div>
            <Input
                prefix="账户:"
                size="large"
                placeholder="账户"
                onChange = {(e) => setUsername(e.target.value)}
                />
          </div>
          <div style={{display: 'flex', marginTop: "5%"}}>
            <Input
                prefix="邮箱:"
                size="large"
                style={{width: "75%"}}
                placeholder="邮箱"
                onChange = {(e) => setEmail(e.target.value)}
                />
            <Button 
                type="primary" 
                size="large" 
                style={{marginLeft: "5%"}}
                onClick={handleSendCode}>发送验证码</Button>
          </div>
          <div>
            <Input
                prefix="验证码:"
                size="large"
                style={{ marginTop: "5%"}}
                placeholder="验证码"
                onChange = {(e) => setCode(e.target.value)}
            />
          </div>
          <div style={{marginTop: "5%"}}>
            <Input.Password
                prefix="新密码:"
                size="large"
                placeholder="密码不可少于6位"
                onChange = {(e) => setPassword(e.target.value)}
                onPressEnter={handleSubmit}
                />
          </div>
          <Button
              type="primary"
              size="large"
              style={{marginLeft: 20, marginTop: "5%"}}
              onClick={handleSubmit}>提交</Button>
          <Button
              type="primary"
              size="large"
              style={{marginLeft: 20, marginTop: "5%"}}
              onClick={handleBack}>返回登录界面</Button>
        </div>
      </header>
    </div>
  );
}

export default Forget;