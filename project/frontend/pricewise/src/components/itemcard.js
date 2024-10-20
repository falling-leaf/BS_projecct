import React, { useState } from 'react';
import { Card, Button, Modal } from 'antd';

const ItemCard = ({item}) => {

  const [showMedal, setShowMedal] = useState(false);
  const [discountName, setDiscountName] = useState("");

  const onSetDiscount = () => {
    console.log("set discount");
  };

  const onShowMedal = () => {
    setShowMedal(true);
    setDiscountName(item.item_name);
  };

  const onCloseMedal = () => {
    setShowMedal(false);
  };

  const onClick = () => {
    console.log("clicked");
    window.location.href = "/detail?name=" + encodeURIComponent(item.item_name);
  };
  return (
    <div className="item-card">
      <Modal title="设置降价提醒" open={showMedal} onOk={() =>onSetDiscount()} onCancel={onCloseMedal}>
        <p>您确定要为【{discountName}】设置降价提醒吗？</p>
      </Modal>
      <Card style = {{ height: "100%"}}>
        <img src={item.image} alt={item.item_name} style={{ width: "50%", height: "50%" }} onClick={onClick} />
        <p onClick={onClick}>{item.item_name}</p>
        <p onClick={onClick}>{item.shop_name}</p>
        <div>
          <div style={{ color: 'red' }}>￥{item.price}</div>
          <Button type = "primary" style={{ float: 'right' }} onClick={() =>onShowMedal(item.item_name)}>设置降价提醒</Button>
        </div>
      </Card>
    </div>
  );
};

export default ItemCard;
