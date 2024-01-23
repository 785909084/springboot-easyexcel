package com.al.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.al.easyexcel.listener.OrderListener;
import com.al.easyexcel.pojo.Orders;
import com.al.easyexcel.service.OrderService;
import com.al.easyexcel.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @Value("${filepath}")
    private String path;
    //全查
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Orders> getAll(Orders orders) {
        return orderService.getAll();
    }

    // easyexcel导出Excel到指定位置
    @GetMapping("/export2File")
    @ResponseBody
    public String export2File() {
        ExcelUtils.export2File(path, "订单表", "订单信息", Orders.class, orderService.getAll());
        return "导出成功";
    }

    // easyexcel导出Excel到web
    @GetMapping("/export2Web")
    public void export2Web(HttpServletResponse response) {
        try {
            ExcelUtils.export2Web(response, "订单表", "订单信息", User.class, orderService.getAll());
        } catch (Exception e) {
            //log.error("报表导出异常:", e);
        }
    }

    // 将指定位置指定名称的Excel导出到web
    @GetMapping("/export2Web4File/{excelName}")
    @ResponseBody
    public String export2Web4File(HttpServletResponse response, @PathVariable String excelName) {
        try {
            return ExcelUtils.export2Web4File(response, path, excelName);
        } catch (Exception e) {
            //log.error("文件导出异常：", e);
        }

        return "文件导出失败";
    }

    // easyexcel读取文件
    @GetMapping("/read4File")
    @ResponseBody
    public String read4File() {
        String fileName = path + "订单信息表.xlsx";
        EasyExcel.read(fileName, Orders.class, new OrderListener()).sheet().doRead();
        return "读取成功";
    }

    // 跳转到上传页面
    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "user/upload";
    }

    // easyexcel上传文件
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new OrderListener()).sheet().doRead();
        return "上传成功";
    }
}
