package com.jiabin.shop;

import com.jiabin.shop.dataobject.ProductCategory;
import com.jiabin.shop.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private ProductCategoryRepository repository;

//    @RequestMapping("/")
//    public String index(ModelMap map) {
//        // 加入一个属性，用来在模板中读取
//        map.addAttribute("host", "http://blog.didispace.com");
//        // return模板文件的名称，对应src/main/resources/templates/index.html
//        return "index";
//    }

    @RequestMapping("/hello")
    public String Hello() throws Exception {
        throw  new Exception("发生错误");
    }

    @GetMapping(value = "/v1")
    public ProductCategory productList(ProductCategory productCategory){

        return repository.save(productCategory);
    }
    @PostMapping("/add1")
    public ProductCategory add1(@RequestParam("categoryId")Integer id,
                                    @RequestParam("categoryName") String categoryName,
                                @RequestParam("categoryType") Integer type){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(id);
        productCategory.setCategoryName(categoryName);
        productCategory.setCategoryType(type);

       return repository.save(productCategory);
    }

    @GetMapping("/delete1")
    public List<ProductCategory> delete1(Integer id){
        repository.deleteById(id);

        return repository.findAll();
    }

}