package com.yangxinyu.service;

import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.Setmeal;

import java.util.List;

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

    /**
     * 查询所有套餐
     * @return
     */
    List<Setmeal> getAll();

    /**
     * 根据套餐id查询套餐详情
     * @param id
     * @return
     */
    Setmeal getSetmealById(Integer id);
}
