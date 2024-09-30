import React, { useEffect, useState } from 'react';
import { useLocation } from'react-router-dom';
import { AppstoreOutlined } from '@ant-design/icons';
import { Menu } from 'antd';
import ItemList from '../components/itemlist.js'

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
  const [searchValue, setSearchValue] = useState('');
  const [current, setCurrent] = useState('jingdong');
  const items = [
    {
      id: '1',
      name: '物品1',
      price: '1000',
      img: '../assets/images/logo.png',
      label: 'jingdong',
      text: '物品1的简介'
    },
    {
      id: '2',
      name: '物品2',
      price: '1000',
      img: '../assets/images/logo.png',
      label: 'taobao',
      text: '物品2的简介'
    }
  ];

  const filteredItems = items.filter(item => item.label === current);

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
  return (
    <div>
      <h2>price-wise: 商品比价网站 - 查阅商品：{Onmounted()}</h2>
      <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={submenu} />
      <ItemList items={filteredItems} />
    </div>
  );
};

export default Menupage;