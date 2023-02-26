package com.yangxinyu.service;

import com.yangxinyu.entity.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    /**
     * 上传Excel
     * 将上解析后的Excel进行封装，传入dao层
     * @param excelRows
     */
    public void upload(List<String[]> excelRows);

    /**
     * 通过年月查询预约表
     * @param month
     * @return
     */

    List<OrderSetting> getOrderSettingByMonth(Integer year, Integer month);
}
