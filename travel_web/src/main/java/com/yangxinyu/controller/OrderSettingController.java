package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.OrderSetting;
import com.yangxinyu.entity.Result;
import com.yangxinyu.poi.POIUtils;
import com.yangxinyu.service.OrderSettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 上传文件
     * @param excelFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            //将Excel解析为所有行的集合
            List<String[]> excelRows = POIUtils.readExcel(excelFile);

            orderSettingService.upload(excelRows);

            return new Result(true,MessageConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            return new Result(false,MessageConstant.UPLOAD_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    private Result getOrderSettingByMonth(Integer year,Integer month){
        List<OrderSetting> orderSettings = orderSettingService.getOrderSettingByMonth(year,month);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,orderSettings);
    }
}
