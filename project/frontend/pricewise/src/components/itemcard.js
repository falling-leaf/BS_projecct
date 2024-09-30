import React from 'react';

const ItemCard = ({item}) => {
  return (
    <div className="item-card">
        <img src={item.img} alt={item.name} />
        <h3>{item.name}</h3>
        <p>{item.text}</p>
        <p>Price: {item.price}</p>
    </div>
  );
};

export default ItemCard;
