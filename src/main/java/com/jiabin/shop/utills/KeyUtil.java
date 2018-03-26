package com.jiabin.shop.utills;

import java.util.Random;

/**
 * @Author: 加冰
 * @Description: 生成格式为时间+随机数的唯一主键  key为约束、索引
 * @Date: Created in 2018/3/26 18:56
 */
public class KeyUtil {

    public static synchronized String generateUniqueKey(){ //同步锁避免重复
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000; //生成6位随机数

        return System.currentTimeMillis() + String.valueOf(number); //类型转化为String
    }
}
