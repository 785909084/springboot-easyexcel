package com.al.easyexcel.service.impl;

import com.al.easyexcel.mapper.OrdersMapper;
import com.al.easyexcel.pojo.Orders;
import com.al.easyexcel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    //批量导入数据库
    @Override
    public void saveBatch(List<Orders>list) {
        for (Orders orders : list) {
            ordersMapper.insert(orders);
        }
    }
    //查询
    @Override
    public List<Orders> getAll() {
        return ordersMapper.selectAll();
    }
}
