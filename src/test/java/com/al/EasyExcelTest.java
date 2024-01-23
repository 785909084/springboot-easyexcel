package com.al;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.al.easyexcel.listener.OrderListener;
import com.al.easyexcel.pojo.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EasyExcelTest {



    public List<Orders> data(){
        List<Orders> ordersList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Orders orders=new Orders();
            orders.setId(i);
            orders.setOrdername("测试数据"+i);
            orders.setOrdernum("0000"+i);
            ordersList.add(orders);
        }
        return ordersList;
    }

    //测试导出文件
    @Test
    public void simpleWrite() {
        String fileName="export" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter=null;
        try {
            //创建写对象
            excelWriter = EasyExcel.write(fileName, Orders.class).build();
            //创建行对象
            WriteSheet writeSheet=EasyExcel.writerSheet("模板").build();
            //写入数据到行中
            excelWriter.write(data(),writeSheet);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(excelWriter!=null){
                excelWriter.finish();//完成之后，关闭流
            }
        }
    }

    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "export1609905005037.xlsx";

        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, Orders.class, new OrderListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            ExcelReader read = excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }
}
