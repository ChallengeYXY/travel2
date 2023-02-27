package com.yangxinyu.dao;

import com.github.pagehelper.Page;
import com.yangxinyu.entity.Setmeal;

import java.util.List;

public interface SetmealDao {
    /**
     * 向套餐游表添加数据
     * @param setmeal
     */
    public void addSetmeal(Setmeal setmeal);


    /**
     * 向套餐游表与跟团游表的中间表插入数据
     * @param setmealId
     * @param travelgroupId
     */
    public void addSetmealAndTravelGroup(Integer setmealId, Integer travelgroupId);

    /**
     * 分页查询套餐游
     * @param queryString
     * @return
     */
    public Page findPage(String queryString);

    List<Setmeal> getAll();

    Setmeal getSetmealById(Integer id);
}
