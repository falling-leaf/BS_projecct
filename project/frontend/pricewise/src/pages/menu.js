import React, { useEffect, useState } from 'react';
import { useLocation } from'react-router-dom';
import { AppstoreOutlined } from '@ant-design/icons';
import { Menu, Typography } from 'antd';
import ItemList from '../components/itemlist.js';
import logo from '../assets/images/logo.png'

const { Text } = Typography;

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
  const prim_items = [
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
  const [items, setItems] = useState(prim_items);
  const [searchValue, setSearchValue] = useState('');
  const [current, setCurrent] = useState('jingdong');
  const [filteredItems, setFilteredItems] = useState(items.filter(item => item.label === current));
  // const filteredItems = items.filter(item => item.label === current);

  // 当current或items改变时，filteredItems也要改变
  // useEffect(() => {
  //   setFilteredItems(memorizedItems.filter(item => item.label === current));
  // }, [current, memorizedItems]);
  useEffect(() => {
    setFilteredItems(items.filter(item => item.label === current));
  }, [current, items]);

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
      <div>
            <Text strong style={{fontSize: '24px'}}>price-wise:查询:{Onmounted()}</Text>
            <Text strong style={{fontSize: '24px', float: 'right'}}>XXX, 欢迎您！</Text>
      </div>
      <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={submenu} style = {{marginTop: '3%'}} />
      <br />
      <ItemList items={filteredItems} />
    </div>
  );
};

export default Menupage;