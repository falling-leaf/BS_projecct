import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { LeftOutlined } from "@ant-design/icons";
import TopPart from "../components/toppart";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

const Detail = () => {
    const [data, setData] = useState([{id: null, item_name: null, price: null, platform: null, shop_name: null, image: null}]);
    const [item_name, setItem_Name] = useState("");
    const [latest_data, setLatest_Data] = useState([]);

    const location = useLocation();

    const [dimensions, setDimensions] = useState({ width: window.innerWidth, height: window.innerHeight });
  
    useEffect(() => {
        const handleResize = () => {
            setDimensions({ width: window.innerWidth * 0.8, height: window.innerWidth * 0.4 });
        };
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    useEffect(() => {
        const fetchData = async () => {
          const query = new URLSearchParams(location.search);
          const search_name = query.get('name');
          setItem_Name(search_name);
          await axios.get('/item/get', {
              params: {
                  name: search_name
              }
          })
          .then(res => {
            console.log(res.data.payload);
            setData(res.data.payload.reverse());
            setLatest_Data(res.data.payload[0]);

          })
          .catch(error => {
              console.log(error);
          });
        };
        fetchData();
      }, []);

    const onBack = () => {
        window.history.back();
    };
    
    return (
        <div>
            <TopPart />
            <div>
                <LeftOutlined onClick={onBack} style = {{margin: '1%', fontSize: '20px', cursor: 'pointer'}} />
            </div>
            <div style = {{display: 'flex', justifyContent: 'center', margin: '1%'}}>
                <h2>【{item_name}】</h2>
            </div>
            <div style = {{display: 'flex', justifyContent: 'center', margin: '1%'}}>
                <div>
                    <img src = {latest_data.image} alt = {item_name} style = {{width: '100%', height: '100%'}} />
                </div>
                <div>
                    <div>商品名称：{item_name}</div>
                    <p />
                    <div>商品最新价格：{latest_data.price}</div>
                    <p />
                    <div>商品来源：{latest_data.platform === null ? '暂无' : latest_data.platform}</div>
                    <p />
                    <div>商品商家：{latest_data.shop_name === null ? '暂无' : latest_data.shop_name}</div>
                </div>
            </div>
            <div style = {{display: 'flex', justifyContent: 'center', margin: '1%'}}>
            价格走势图
            </div>
            <div style = {{marginTop: '1%', width: '80%', height: '300px'}}>
                <LineChart width={dimensions.width} height={dimensions.height} data={data.reverse()}>
                    <XAxis dataKey="item_time" />
                    <YAxis />
                    <CartesianGrid strokeDasharray="3 3" />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="price" stroke="#8884d8" activeDot={{ r: 8 }} />
                </LineChart>
            </div>
        </div>
    );
};

export default Detail;