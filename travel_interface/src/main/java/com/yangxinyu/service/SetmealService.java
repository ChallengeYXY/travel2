package com.yangxinyu.service;

import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.Setmeal;

public interface SetmealService {
    /**
     * 上传套餐游图片
     */
    public void uploadPicture(byte[] bytes, String newPictureName);

    /**
     * 添加套餐游
     * @param travelgroupIds
     * @param setmeal
     */
    public void addSetmeal(Integer[] travelgroupIds, Setmeal setmeal);

    /**
     * 分页查询套餐游
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean);
}
