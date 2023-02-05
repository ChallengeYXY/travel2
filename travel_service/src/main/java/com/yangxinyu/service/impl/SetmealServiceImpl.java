package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yangxinyu.dao.SetmealDao;
import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.Setmeal;
import com.yangxinyu.qiniu.QiniuUtils;
import com.yangxinyu.qiniu.RedisConstant;
import com.yangxinyu.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;
    /**
     * 向七牛云上传图片
     * @param bytes
     * @param newPictureName
     */
    @Override
    public void uploadPicture(byte[] bytes, String newPictureName) {
        QiniuUtils.upload2Qiniu(bytes,newPictureName);
        //上传成功后将文件名放入Redis的set
        Jedis jedisPoolResource = jedisPool.getResource();
        jedisPoolResource.select(0);
        jedisPoolResource.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newPictureName);
    }

    /**
     * 添加套餐游
     * @param travelgroupIds
     * @param setmeal
     */
    @Override
    public void addSetmeal(Integer[] travelgroupIds, Setmeal setmeal) {
        //添加套餐基本信息
        setmealDao.addSetmeal(setmeal);
        //获取回填主键
        Integer setmealId = setmeal.getId();
        //添加套餐游与跟团游中间表
        for (Integer travelgroupId : travelgroupIds) {
            setmealDao.addSetmealAndTravelGroup(setmealId,travelgroupId);
        }

        //从后向前找到第一个/的位置，截取图片名
        int index = setmeal.getImg().lastIndexOf("/");
        String imgName = setmeal.getImg().substring(index+1);
        //将存储到数据库的图片名放到Redis的set
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,imgName);
    }

    /**
     * 分页查询套餐游
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //开启分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        //查询当前页
        Page page = setmealDao.findPage(queryPageBean.getQueryString());

        //返回分页数据（总条数与当前页内容）
        return new PageResult(page.getTotal(),page.getResult());
    }
}
