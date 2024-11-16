import React, { useState } from 'react';
import { Modal, Dropdown, Space, Typography } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import "./TopPart.css"

const { Text } = Typography;

const TopPart = () => {
    const [isLogoutOpen, setIsLogoutOpen] = useState(false);

    const handleShowLogout = () => {
        setIsLogoutOpen(true);
    }

    const handleMyDiscount = () => {
        console.log('my discount');
        window.location.href = '/mydiscount';
    }

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsLogoutOpen(false);
        window.location.href = '/login';
    }

    const items = [
        {
            key: '1',
            label: '欢迎您，' + (localStorage.getItem('username') === null ? '游客' : localStorage.getItem('username')),
        },
        {
            key: '2',
            label: (
                <div onClick={handleMyDiscount}>我的关注</div>
            ),
        },
        {
          key: '3',
          label: (
            <div onClick={handleShowLogout} style={{color: 'red'}}>退出登录</div>
          ),
        },
      ];
  return (
    <div>
        <Modal
            title="确认退出登录"
            open={isLogoutOpen}
            onOk={handleLogout}
            onCancel={() => setIsLogoutOpen(false)}
            okText="确认"
            cancelText="取消"
        >
            你确定要退出登录吗？
        </Modal>
        <div className='search-header'>
            <Text strong style={{fontSize: '24px'}}>price-wise</Text>
            <div style={{fontSize: '30px', float: 'right', marginRight: "1%"}}>
                <Dropdown
                    trigger='click'
                    menu={{
                        items,
                    }}
                    >
                    <div onClick={(e) => e.preventDefault()}>
                        <Space>
                        <UserOutlined />
                        </Space>
                    </div>
                </Dropdown>
            </div>
        </div>
    </div>
    );
}

export default TopPart;