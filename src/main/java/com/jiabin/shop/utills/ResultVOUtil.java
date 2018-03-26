package com.jiabin.shop.utills;

import com.jiabin.shop.viewobject.ResultVO;

/**
 * @Author: 加冰
 * @Description: 每一个接口返回result对象时，不用一直new
 * @Date: Created in 2018/3/25 13:27
 */
public class ResultVOUtil {

    /*静态方法*/
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);

        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
