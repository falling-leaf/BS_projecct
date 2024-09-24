import React, { useEffect, useState } from 'react';
import { useLocation } from'react-router-dom';
import { AppstoreOutlined, MailOutlined, SettingOutlined } from '@ant-design/icons';
import { Menu } from 'antd';
const items = [
  {
    label: 'Navigation One',
    key: 'mail',
    icon: <MailOutlined />,
  },
  {
    label: 'Navigation Two',
    key: 'app',
    icon: <AppstoreOutlined />,
    disabled: true,
  },
  {
    label: 'Navigation Three - Submenu',
    key: 'SubMenu',
    icon: <SettingOutlined />,
    children: [
      {
        type: 'group',
        label: 'Item 1',
        children: [
          {
            label: 'Option 1',
            key: 'setting:1',
          },
          {
            label: 'Option 2',
            key: 'setting:2',
          },
        ],
      },
      {
        type: 'group',
        label: 'Item 2',
        children: [
          {
            label: 'Option 3',
            key: 'setting:3',
          },
          {
            label: 'Option 4',
            key: 'setting:4',
          },
        ],
      },
    ],
  },
  {
    key: 'alipay',
    label: (
      <a href="https://ant.design" target="_blank" rel="noopener noreferrer">
        Navigation Four - Link
      </a>
    ),
  },
];
const Menupage = () => {
  const [searchValue, setSearchValue] = useState('');
  const [current, setCurrent] = useState('mail');

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
      <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={items} />
    </div>
  );
};
export default Menupage;