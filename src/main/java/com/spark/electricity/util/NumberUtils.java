package com.spark.electricity.util;

import java.math.BigDecimal;

/**
 * @ClassName NumberUtils：数字工具类
 * @Time : 2020/8/11 0011 14:52
 * @Author : lisheng
 * @Description:
 **/
public class NumberUtils {

    /**
     * @Author lisheng
     * @Description //TODO 格式化小数，
     * @Date 14:54 2020/8/11 0011
     * @Param [num, scale]scale:四舍五入的位数
     * @return double
     **/
    public static double  formatDouble(double num,int scale){
        BigDecimal bd = new BigDecimal(num);
        return bd.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
