import React from "react";
import "./itemcard.js"
import ItemCard from "./itemcard.js";

const ItemList = ({ items }) => {
  return (
    <div className="item-list" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px' }}>
        {items.map((item) => (
          <div key={item.id} style={{ flex: '0 0 calc(25% - 20px)', marginBottom: '20px' }}>
          <ItemCard key={item.id} item={item} />
          </div>
        ))}
        {
          items.length === 0 &&
          <div className="no-items">No items found</div>  
        }
    </div>
  );
};

export default ItemList;