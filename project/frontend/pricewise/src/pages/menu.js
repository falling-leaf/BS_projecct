import React, { useEffect, useState } from 'react';
import { useLocation } from'react-router-dom';
import { AppstoreOutlined, LoadingOutlined } from '@ant-design/icons';
import { Input, Menu, Spin, message } from 'antd';
import axios from 'axios';
import ItemList from '../components/itemlist.js';
import TopPart from '../components/toppart.js';

const { Search } = Input;

const submenu = [
  {
    label: '京东搜索结果',
    key: 'jingdong',
    icon: <AppstoreOutlined />,
  },
  {
    label: '唯品会搜索结果',
    key: 'vip',
    icon: <AppstoreOutlined />,
  },
  {
    label: '亚马逊搜索结果',
    key: 'amazon',
    icon: <AppstoreOutlined />,
  }
];

const Menupage = () => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [searchValue, setSearchValue] = useState('');
  const [newSearchValue, setNewSearchValue] = useState('');
  const [current, setCurrent] = useState('jingdong');
  const [filteredData, setFilteredData] = useState([]);

  const location = useLocation();

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      const query = new URLSearchParams(location.search);
      const searching = query.get('search');
      setSearchValue(searching);
      await axios.get('/item/insert', {
          params: {
              input: searching
          }
      })
      .then(res => {
        console.log(res.data.payload);
        console.log(res.data.message);
        setData(res.data.payload);
      })
      .catch(error => {
          console.log(error);
          message.error('error');
      });
      setIsLoading(false);
      console.log()
    };
    fetchData();
  }, []);

  useEffect(() => {
    if (data !== null)
    setFilteredData(data.filter(item => item.platform === current));
  }, [current, data]);

  const onClick = (e) => {
    console.log('click ', e);
    setCurrent(e.key);
  };

  const handleSearchChange = (e) => {
    setNewSearchValue(e.target.value);
  };

  const handleSearch = () => {
    console.log(newSearchValue);
    window.location.href = '/menu?search=' + newSearchValue;
  };
  return (
    <div>
      <TopPart />
      <div style = {{textAlign: 'center'}}>
        <Search 
              placeholder="请输入商品名称" 
              size = "large" 
              style = {{width: '50%', marginTop: '2%'}}
              onChange = {handleSearchChange}
              onSearch={handleSearch} enterButton />
      </div>
      <div>正在搜索：{searchValue}</div>
      <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={submenu} />
      <br />
      <div> {isLoading ? <div style={{display: 'flex', justifyContent: 'center', marginTop: '15%'}}>
              <Spin indicator={<LoadingOutlined spin />} size="large" />
              <p />
              <p>正在加载中，请稍候...</p>
            </div> : <ItemList items={filteredData} />} </div>
    </div>
  );
};

export default Menupage;