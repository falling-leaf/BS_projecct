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
        <div className="no-items" style={{ gridColumn: 'span 4' }}>
          <div>
            No items found.
          </div>
          <p />
          <div>
            若正在使用阿里巴巴平台，请在非docker环境下，在含Microsoft Edge 128浏览器的Windows操作系统下运行。
          </div>
          <p />
          <div>
            若正在使用京东平台，请及时更换cookie认证权限。
          </div>
        </div>  
        
      }
    </div>
  );
};

export default ItemList;