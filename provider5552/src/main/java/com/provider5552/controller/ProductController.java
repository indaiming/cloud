package com.provider5552.controller;

import com.provider5552.entity.Product;
import com.provider5552.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("product")
@Api("商品表数据模型")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("getTenProduct")
    @ApiOperation("查询十个商品表详情")
    public List<Product> getAllProduct(){
        return productService.findAll().subList(0,10);
    }
    @GetMapping("/get/{id}")
    @ApiOperation("查询单个商品表详情")
    public Product getProduct(@PathVariable Long id){
        return productService.findOne(id);
    }

    @PostMapping("/save")
    @ApiOperation("新增商品表详情")
    public Product saveProduct(@RequestBody Product product ){
        return productService.save(product);
    }

//    {
//        "id": 0,
//            "title": "測試20190526",
//            "sellpoint": "不怎麽好啊",
//            "price": 232320,
//            "num": 10,
//            "image": "www.tup.com",
//            "cid": 560,
//            "status": 1
//    }

    @PostMapping("/update")
    @ApiOperation("更新商品表详情")
    public void updateProduct(@RequestBody Product product ){
        productService.saveAndFlush(product);
    }
    @GetMapping("/delete/{id}")
    @ApiOperation("删除商品表详情")
    public void deleteProduct(@PathVariable long id ){
        productService.delete(id);
    }
}
