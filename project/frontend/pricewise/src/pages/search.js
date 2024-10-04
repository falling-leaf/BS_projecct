import React, { useState } from 'react';
import { Input, message, Typography, List } from 'antd';
import './search.css';
const { Text } = Typography;

const Searchpage = () => {  
    const [searchValue, setSearchValue] = useState(''); 
    const [data, setData] = useState(['商品1', '商品2', '商品3', '商品4', '商品5', '商品6', '商品7', '商品8', '商品9', '商品10']);
    const handleSearch = () => {
        if (searchValue === '') {
            ToLessMessage();
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

    const ToLessMessage = () => {
        message.info('请输入商品名称');
    }

  return (
    <div>
        <div>
            <Text strong style={{fontSize: '24px'}}>price-wise:搜索</Text>
            <Text strong style={{fontSize: '24px', float: 'right'}}>XXX, 欢迎您！</Text>
        </div>
        <div style = {{textAlign: 'center', marginTop: '5%'}}>
            <h2>查询商品，发现更多好物：</h2>
            <p />
            <Input 
            placeholder="请输入商品名称" 
            size="large" 
            value={searchValue}
            onChange = {handleInputChange}
            onPressEnter={handleSearch}
            style={{width: '50%'}}/>
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