package com.jiabin.shop.controller;

import com.jiabin.shop.dataobject.BeefInfo;
import com.jiabin.shop.dataobject.ProductCategory;
import com.jiabin.shop.service.BeefInfoService;
import com.jiabin.shop.service.CategoryService;
import com.jiabin.shop.utills.ResultVOUtil;
import com.jiabin.shop.viewobject.BeefInfoVO;
import com.jiabin.shop.viewobject.BeefVO;
import com.jiabin.shop.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/24 16:08
 */
@RestController
@RequestMapping("/buyer/beef")
public class BuyerBeefController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BeefInfoService beefInfoService;

    @GetMapping("/list")
    public ResultVO list(){
     /*1.拿到所有上架牛肉  先进行查询 如果直接在for循环里查询 数据量多是花销巨大*/
        List<BeefInfo> beefInfoList = beefInfoService.findUpAll();
     /*2.拿到上架牛肉的所有类目*/
     //  * 传统方法
     //     * 000---类目容器
        List<Integer> categoryTypeList = new ArrayList<>();
        for (BeefInfo beefInfo:beefInfoList){
     //       000---装表 将所有上架牛肉的类型装入数组中
            categoryTypeList.add(beefInfo.getCategoryType());
        }

          /*精简方法 java8lambda*/
//          List<Integer> categoryTypeList = beefInfoList.stream()
//                  .map(e -> e.getCategoryType())
//                  .collect(Collectors.toList());

          /*000---根据上架商品的类目编号索引出类目表*/
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
    /*3.数据拼装到Json*/
        List<BeefVO> beefVOList = new ArrayList<>(); /*A每种类目的牛肉列表展示容器*/
        for(ProductCategory productCategory : productCategoryList){
            BeefVO beefVO = new BeefVO();/*B每种牛肉*/
            beefVO.setCategoryType(productCategory.getCategoryType());
            beefVO.setCategoryName(productCategory.getCategoryName());

            List<BeefInfoVO> beefInfoVOList = new ArrayList<>();/*C1牛肉具体内容规格的列表容器*/
            for(BeefInfo beefInfo:beefInfoList){
                if (beefInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    /*C2详情（价格图片描述）容器*/
                    BeefInfoVO beefInfoVO = new BeefInfoVO();
                    //属性复制
                   // BeanUtils.copyProperties(beefInfo,beefInfoVO);
                    beefInfoVO.setBeefId(beefInfo.getBeefId());
                    beefInfoVO.setBeefName(beefInfo.getBeefName());
                    beefInfoVO.setBeefPrice(beefInfo.getBeefPrice());
                    beefInfoVO.setBeefDescription(beefInfo.getBeefDescription());
                    beefInfoVO.setBeefIcon(beefInfo.getBeefIcon());
                    /*C3将一个详情装到详情列表*/
                    beefInfoVOList.add(beefInfoVO);
                    System.out.println("牛肉详情：" + beefInfoVO);
                }
            }
            beefVO.setBeefInfoVOList(beefInfoVOList);/*C每种牛肉的详情列表*/
            //是什么？？？？？？ 每个类目是一个beefVO 可数据加的是类目表 没有将类目加进类目标出现断层
            beefVOList.add(beefVO);//!!!!!!!!
            System.out.println("牛肉详情列表："+ beefInfoList);
        }

        System.out.println("牛肉列表"+ beefVOList);



/*
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        *//*将牛肉数据装到数据里*//*
        resultVO.setData(beefVOList);
        //里面是个list用Arrays
       // beefVO.setBeefInfoVOList(Arrays.asList(beefInfoVO));
        return resultVO;
        */
        return ResultVOUtil.success(beefVOList);
    }
}
