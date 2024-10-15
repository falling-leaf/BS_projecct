import React, { useState } from 'react';
import { Input, message, Typography, List, Dropdown, Space, Modal } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import './search.css';
const { Search } = Input;
const { Text } = Typography;



const Searchpage = () => {  
    const [searchValue, setSearchValue] = useState(''); 
    const [isLogoutOpen, setIsLogoutOpen] = useState(false);
    const [data, setData] = useState(['商品1', '商品2', '商品3', '商品4', '商品5', '商品6', '商品7', '商品8', '商品9', '商品10']);

    const handleShowLogout = () => {
        setIsLogoutOpen(true);
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
            label: '个人中心',
        },
        {
          key: '3',
          label: (
            <div onClick={handleShowLogout} style={{color: 'red'}}>退出登录</div>
          ),
        },
      ];
    

    const handleSearch = () => {
        if (searchValue === '') {
            TooLessMessage();
        } else {
            window.location.href = '/menu?search=' + searchValue;
        }
    }

    const OnItemClick = (item) => {
        // console.log(item);
        window.location.href = '/menu?search=' + item;
    }

    const handleInputChange = (e) => {
        setSearchValue(e.target.value);
    }

    const TooLessMessage = () => {
        message.info('请输入商品名称');
    }

  return (
    <div style={{height: "100vh"}}>
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
            <Text strong style={{fontSize: '24px'}}>price-wise:搜索</Text>
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
        <div style = {{textAlign: 'center', marginTop: '5%'}}>
            <h2>查询商品，发现更多好物：</h2>
            <p />
            <Search 
            placeholder="请输入商品名称" 
            size = "large" 
            style = {{width: '50%'}}
            onChange = {handleInputChange} 
            onSearch={handleSearch} enterButton />
        </div>
        <div style={{ display: 'flex' }}>
            <List
                header={<div><h2>热门商品</h2></div>}
                style = {{marginLeft: '5%', marginTop: '5%', width: '40%'}}
                bordered
                dataSource={data}
                renderItem={(item) => (
                    <List.Item>
                        <List.Item.Meta
                            title={<div onClick={() => OnItemClick(item)}>{item}</div>}
                        />
                    </List.Item>
                )}
            />
            <List
                header={<div><h2>搜索历史</h2></div>}
                style = {{marginTop: '5%', width: '40%', marginLeft: '10%'}}
                bordered
                dataSource={data}
                renderItem={(item) => (
                    <List.Item>
                        <List.Item.Meta
                            title={<div onClick={() => OnItemClick(item)}>{item}</div>}
                        />
                    </List.Item>
                )}
            />
            
        </div>
    </div>
    );
}

export default Searchpage;