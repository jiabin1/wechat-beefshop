package com.jiabin.shop.utills;

import com.jiabin.shop.enums.CodeEnum;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/28 16:45
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumsClass){
        for (T each: enumsClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each; //返回枚举类型
            }
        }
        return null;
    }
}
