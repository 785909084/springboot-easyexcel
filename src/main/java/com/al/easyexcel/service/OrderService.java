package com.al.easyexcel.service;

import com.al.easyexcel.pojo.Orders;

import java.util.List;

public interface OrderService {
    //批量导入数据到数据库中
    public void saveBatch(List<Orders>list);

    public List<Orders> getAll();
}
