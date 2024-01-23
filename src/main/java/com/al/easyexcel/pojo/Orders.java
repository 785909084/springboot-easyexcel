package com.al.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
//@Table(name = "orders")
public class Orders {
    @ExcelProperty(value = "序号",index = 0)
    private Integer id;
    @ExcelProperty(value = "订单号码",index = 1)
    private String ordernum;
    @ExcelProperty(value = "收货人",index = 2)
    private String  ordername;




}
