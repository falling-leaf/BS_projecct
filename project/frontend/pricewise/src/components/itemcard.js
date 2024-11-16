import React, { useState } from 'react';
import { Card, Button, Modal, message } from 'antd';
import axios from 'axios';

const ItemCard = ({item}) => {

  const [showMedal, setShowMedal] = useState(false);
  const [discountName, setDiscountName] = useState("");

  const onSetDiscount = () => {
    axios.post('/discount/insert', null, {
      params: {
          jwt_value: localStorage.getItem('token'),
          item_name: item.item_name,
          price: Number(item.price),
          item_time: item.item_time,
          platform: item.platform
      }
  })
  .then(res => {
    if (res.data.message === "ok") {
      message.success("设置成功");
      setShowMedal(false);
    } else if (res.data.message === "invalid token"){
        message.error("登录token已失效，请重新登录");
        setTimeout(() => {
            window.location.href = '/login';
        }, 1000);
    } else {
      message.error(res.data.message);
    }
  })
  .catch(error => {
      console.log(error);
  });
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
