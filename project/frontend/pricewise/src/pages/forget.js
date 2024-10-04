import React from 'react';
import { Input, Button } from 'antd';

const Forget = () => {  

  const handleBack = () => {
    window.location.href = '/login';
  }


  return (
    <div className = "Login">
      <header className = "Login-header">
          <div>
            <h2>忘记密码</h2>
            <h3>通过邮箱验证码重置密码！</h3>
            <div style={{ display: 'flex' }}>
              <Input 
                prefix = "邮箱:" 
                size = "large" 
                style={{ width: "50%", marginLeft: 50 }} 
                placeholder="邮箱" />
              <Button type="primary" size="large" style={{marginLeft: 20}}>发送验证码</Button>
            </div>
            <br />
            <div>
              <Input
                prefix="验证码:"
                size="large"
                style={{ width: "55%", marginTop: 20 }}
                placeholder="验证码"
              />
              <Button type="primary" size="large" style={{marginLeft: 20, marginTop: 20}}>提交</Button>
            </div>
            <br />
            <Button 
            type="primary" 
            size="large" 
            style={{marginLeft: 20, marginTop: 20}}
            onClick={handleBack}>返回登录界面</Button>
          </div>
      </header>
    </div>
    );
}

export default Forget;