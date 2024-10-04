import React from 'react';
import { Card } from 'antd';

const ItemCard = ({item}) => {

  const onClick = () => {
    console.log("clicked");
    window.location.href = "/detail?id=" + item.id;
  };
  return (
    <div className="item-card">
      <Card title={item.name} style={{ width: "100%" }} onClick={onClick}>
        <img src={item.image} alt={item.name} style={{ width: "100%" }} />
        <p>{item.text}</p>
        <div style={{ color: 'red' }}>￥{item.price}</div>
      </Card>
    </div>
  );
};

export default ItemCard;
