package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yangxinyu.dao.OrderSettingDao;
import com.yangxinyu.entity.OrderSetting;
import com.yangxinyu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    /**
     * 上传Excel
     * 将上解析后的Excel进行封装，传入dao层
     * @param excelRows
     */
    @Override
    public void upload(List<String[]> excelRows) {
        //创建一个OrderSetting对象
        OrderSetting orderSetting = new OrderSetting();
        //将每行封装为OrderSetting对象
        for (String[] excelRow : excelRows) {
            orderSetting.setOrderDate(new Date(excelRow[0]));
            orderSetting.setNumber(Integer.parseInt(excelRow[1]));
            //判断当前日期是否存在
            int count = orderSettingDao.getOrderSettingByOrderDate(orderSetting.getOrderDate());
            System.out.println(count);
            if (count>0){
                //当前日期存在，根据日期修改当前数量
                orderSettingDao.updateOrderSettingByOrderDate(orderSetting);
            }else {
                orderSettingDao.addOrderSetting(orderSetting);
            }
        }
    }
}
