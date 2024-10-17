import React from 'react';
import { Card } from 'antd';

const ItemCard = ({item}) => {

  const onClick = () => {
    console.log("clicked");
    window.location.href = "/detail?id=" + item.id;
  };
  return (
    <div className="item-card">
      <Card onClick={onClick} style = {{ height: "100%"}}>
        <img src={item.image} alt={item.item_name} style={{ width: "50%", height: "50%" }} />
        <p>{item.item_name}</p>
        <p>{item.shop_name}</p>
        <div style={{ color: 'red' }}>￥{item.price}</div>
      </Card>
    </div>
  );
};

export default ItemCard;
