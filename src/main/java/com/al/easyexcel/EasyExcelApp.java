package com.al.easyexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.heima.mapper")
public class EasyExcelApp {
    public static void main(String[] args) {
        SpringApplication.run(EasyExcelApp.class);
    }
}
