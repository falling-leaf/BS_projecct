import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Image, Typography } from "antd";
import logo from '../assets/images/logo.png'

const { Text } = Typography;

const Detail = () => {
    const [target, setTarget] = React.useState("");

    const Onmounted = () => {
        URLcomponent();
        return target.name;
    };

    const URLcomponent = () => {
        const location = useLocation();
        const query = new URLSearchParams(location.search);
        const prim_target = {
            id: query.get('id'),
            name: '物品1',
            price: '1000',
            image: logo,
            label: 'taobao',
            text: '物品2的简介'
        };
        useEffect(() => {
          setTarget(prim_target);
        }, [prim_target]);
        return target.name;
    };
    
    return (
        <div>
            <div>
                <Text strong>返回</Text>
            </div>
            <div>
                <Image src={target.image} alt={target.name} style={{ width: "20%", height: "20%" }} />
            </div>
            <div>
                {Onmounted()}
            </div>
        </div>
    );
};

export default Detail;