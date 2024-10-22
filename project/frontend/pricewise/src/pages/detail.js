import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { LeftOutlined } from "@ant-design/icons";
import { Descriptions } from "antd";
import TopPart from "../components/toppart";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const Detail = () => {
    const [data, setData] = useState([{id: null, item_name: null, price: null, platform: null, shop_name: null, image: null}]);
    const [item_name, setItem_Name] = useState("");
    const [latest_data, setLatest_Data] = useState([]);

    const location = useLocation();

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
                    <Descriptions title="商品信息" layout="vertical" bordered="true" style={{width: '80%', marginLeft: '5%'}}>
                        <Descriptions.Item label="商品名称">{item_name}</Descriptions.Item>
                        <Descriptions.Item label="商品最新价格">{latest_data.price}</Descriptions.Item>
                        <Descriptions.Item label="商品来源">{latest_data.platform === null ? '暂无' : latest_data.platform}</Descriptions.Item>
                        <Descriptions.Item label="商品商家">{latest_data.shop_name === null ? '暂无' : latest_data.shop_name}</Descriptions.Item>
                    </Descriptions>
                </div>
            </div>
            <div style = {{display: 'flex', justifyContent: 'center', margin: '1%'}}>
            价格走势图
            </div>
            <ResponsiveContainer width="80%" height={400} style={{display: 'flex', justifyContent: 'center', margin: '1%'}}>
                <LineChart data={data}>
                    <XAxis dataKey="item_time" />
                    <YAxis />
                    <CartesianGrid strokeDasharray="3 3" />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="price" stroke="#8884d8" activeDot={{ r: 8 }} />
                </LineChart>
            </ResponsiveContainer>
        </div>
    );
};

export default Detail;