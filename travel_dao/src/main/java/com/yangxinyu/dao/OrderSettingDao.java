package com.yangxinyu.dao;


import com.yangxinyu.entity.OrderSetting;

import java.util.Date;

public interface OrderSettingDao {
    /**
     * 向预约管理表中添加数据
     * 添加日期与数量
     * @param orderSetting
     */
    public void addOrderSetting(OrderSetting orderSetting);

    /**
     * 根据日期查询预约管理
     * @param orderDate
     * @return 返回查询行数
     */
    public int getOrderSettingByOrderDate(Date orderDate);

    /**
     * 根据日期修改预约管理
     * @param orderSetting
     */
    public void updateOrderSettingByOrderDate(OrderSetting orderSetting);
}
