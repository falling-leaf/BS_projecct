import React, { useState, useEffect } from "react";
import { Table, Button, message } from "antd";
import axios from "axios";
import TopPart from '../components/toppart';

const MyDiscount = () => {
  

  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
        await axios.get('/discount/get', {
            params: {
                jwt_value: localStorage.getItem('token')
            }
        })
        .then(res => {
            if (res.data.message === "invalid token") {
                message.error("登录token已失效，请重新登录");
                setTimeout(() => {
                    window.location.href = '/login';
                }, 1000);
            } else {
                setData(res.data.payload);
            }
        })
       .catch(error => {
            console.log(error);
        });
    };
    fetchData();
  }, []);

  const renewData = () => {
    axios.get('/discount/get', {
        params: {
            jwt_value: localStorage.getItem('token')
        }
    })
    .then(res => {
        if (res.data.message === "invalid token") {
            message.error("登录token已失效，请重新登录");
            setTimeout(() => {
                window.location.href = '/login';
            }, 1000);
        } else {
            setData(res.data.payload);
        }
    })
   .catch(error => {
        console.log(error);
    });
  };

  const columns = [
    {
      title: '商品名称',
      dataIndex: 'item_name',
      key: 'item_name',
    },
    {
      title: '商品价格',
      dataIndex: 'price',
      key: 'price',
    },
    {
      title: '最近更新时间',
      dataIndex: 'item_time',
      key: 'item_time',
    },
    {
      title: '操作',
      key: 'action',
      render: (_, record) => (
        <Button type="primary" onClick={() => {onSetoffDiscount(record)}}>取消关注</Button>
      ),
    },
  ];

  const onSetoffDiscount = (record) => {
    axios.delete('/discount/delete', {
        params: {
            jwt_value: localStorage.getItem('token'),
            item_name: record.item_name
        }
    })
    .then(res => {
        if (res.data.message === "invalid token") {
            message.error("登录token已失效，请重新登录");
            setTimeout(() => {
                window.location.href = '/login';
            }, 1000);
        } else if (res.data.message === "ok") {
            message.success("取消关注成功");
            renewData();
        } else {
            message.error(res.data.message);
        }
    })
   .catch(error => {
        console.log(error);
    });
  }

  const onBack = () => {
    window.history.back();
  }

  return (
    <div>
        <TopPart />
        <div style={{textAlign: 'center'}}>
            <div>
                <Button type="primary" onClick={onBack} style={{float: 'left'}}>返回</Button>
                <h2>我的关注</h2>
            </div>
            <Table dataSource={data} columns={columns} />
        </div>
    </div>
  );
};

export default MyDiscount;