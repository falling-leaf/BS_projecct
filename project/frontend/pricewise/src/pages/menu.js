import React, { useEffect, useState } from 'react';
import { useLocation } from'react-router-dom';
import { AppstoreOutlined } from '@ant-design/icons';
import { Input, Menu } from 'antd';
import ItemList from '../components/itemlist.js';
import TopPart from '../components/toppart.js';
import logo from '../assets/images/logo.png'

const { Search } = Input;

const submenu = [
  {
    label: '京东搜索结果',
    key: 'jingdong',
    icon: <AppstoreOutlined />,
  },
  {
    label: '淘宝搜索结果',
    key: 'taobao',
    icon: <AppstoreOutlined />,
  }
];

const Menupage = () => {
  const prim_data = [
    {
      id: '1',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '2',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '3',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '4',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '5',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '6',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '7',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '8',
      name: '物品1',
      price: '1000',
      image: logo,
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '9',
      name: '物品2',
      price: '1000',
      image: logo,
      label: 'taobao',
      text: '物品2的简介'
    }
  ];
  const [data, setData] = useState(prim_data);
  const [searchValue, setSearchValue] = useState('');
  const [newSearchValue, setNewSearchValue] = useState('');
  const [current, setCurrent] = useState('jingdong');
  const [filteredData, setFilteredData] = useState(data.filter(item => item.label === current));

  useEffect(() => {
    setFilteredData(data.filter(item => item.label === current));
  }, [current, data]);

  const Onmounted = () => {
    URLcomponent();
    return searchValue;
  };
  const onClick = (e) => {
    console.log('click ', e);
    setCurrent(e.key);
  };

  const URLcomponent = () => {
    const location = useLocation();
    const query = new URLSearchParams(location.search);
    const searchValue = query.get('search');
    useEffect(() => {
      setSearchValue(searchValue);
    }, [searchValue]);
    return searchValue;
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
      <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={submenu} style = {{marginTop: '3%'}} />
      <br />
      <ItemList items={filteredData} />
    </div>
  );
};

export default Menupage;