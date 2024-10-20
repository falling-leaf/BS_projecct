import React from "react";
import "./itemcard.js"
import ItemCard from "./itemcard.js";
import "./itemlist.css"


const ItemList = ({ items }) => {
  return (
    <div className="item-list">
      {items.map((item) => (
        <div key={item.id} style={{ height: '100%' }}>
          <ItemCard key={item.id} item={item} style={{ height: '100%' }} />
        </div>
      ))}
      {
        items.length === 0 &&
        <div className="no-items" style={{ gridColumn: 'span 4' }}>No items found</div>  
      }
    </div>
  );
};

export default ItemList;