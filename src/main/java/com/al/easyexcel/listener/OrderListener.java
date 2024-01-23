package com.al.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.al.easyexcel.pojo.Orders;
import com.al.easyexcel.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建回调监听器，监听器用于处理EasyExcel中读取的数据
 */
@Slf4j
public class OrderListener extends AnalysisEventListener<Orders> {
    @Autowired
    private OrderService orderService;

    /**
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Orders> list = new ArrayList<Orders>();

    /**
     * 每解析一条数据都会来调用这个方法
     * @param orders
     * @param analysisContext
     */
    @Override
    public void invoke(Orders orders, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(orders));
        list.add(orders);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 将读取到的数据存储到数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        //存储到数据库
        if (!CollectionUtils.isEmpty(list)) {
            orderService.saveBatch(list);
        }
        log.info("存储数据库成功！");
    }

    /**
     * 每个sheet的所有数据解析完成了 都会来调用这个方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }
}
