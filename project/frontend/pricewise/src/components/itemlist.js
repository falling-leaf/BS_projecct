import React from "react";
import "./itemcard.js"
import ItemCard from "./itemcard.js";

const ItemList = ({ items }) => {
  return (
    <div className="item-list">
        {items.map((item) => (
          <ItemCard key={item.id} item={item} />
        ))}
        {
          items.length === 0 &&
          <div className="no-items">No items found</div>  
        }
    </div>
  );
};

export default ItemList;