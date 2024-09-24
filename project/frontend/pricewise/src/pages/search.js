import React, { useState } from 'react';
import { Input, notification } from 'antd';

const Searchpage = () => {  
    const [searchValue, setSearchValue] = useState('');      
    const handleSearch = () => {
        if (searchValue.key === 'Enter') {
            openNotification();
        } else {
            console.log(searchValue);
            window.location.href = '/menu?search=' + searchValue;
        }
    }
    const handleInputChange = (e) => {
        setSearchValue(e.target.value);
    }

    const openNotification = () => {
        notification.error({
            message: '提示',
            description: '请输入商品名称',
            duration: 2,
        });
    }

  return (
    <div>
        <div style = {{ display: 'flex', justifyContent: 'flex-end', alignItems: 'flex-start', padding: '20px', boxSizing: 'border-box' }}>
            <h2>XXX,欢迎您！</h2>
        </div>
        <div style = {{textAlign: 'center', marginTop: '10%'}}>
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
    </div>
    );
}

export default Searchpage;