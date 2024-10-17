import React from "react";
import "./itemcard.js"
import ItemCard from "./itemcard.js";


const ItemList = ({ items }) => {
  return (
    <div className="item-list" style={{
      display: 'grid',
      gridTemplateColumns: 'repeat(4, 1fr)', // 创建4列，每列宽度相等
      gap: '20px', // 设置网格项之间的间距
      width: '100%', // 使容器宽度为100%
      alignItems: 'stretch',
    }}>
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