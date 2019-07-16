package com.provider5552.entity;

//CREATE TABLE `product` (
//        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
//        `title` varchar(100) NOT NULL COMMENT '商品标题',
//        `sellpoint` varchar(500) DEFAULT NULL COMMENT '商品卖点',
//        `price` bigint(20) NOT NULL COMMENT '商品价格，单位为：分',
//        `num` int(10) NOT NULL COMMENT '库存数量',
//        `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
//        `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
//        `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
//        PRIMARY KEY (`id`),
//        KEY `cid` (`cid`),
//        KEY `status` (`status`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=1473158237 DEFAULT CHARSET=utf8 COMMENT='商品表';


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table
@Table(name = "product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "商品表数据模型")
public class Product {

    @Id
    @Column(name="id",columnDefinition="商品id，同时也是商品编号")
    @ApiModelProperty("商品id，同时也是商品编号")
    private long id;

    @Column(name="title",columnDefinition="商品标题")
    @ApiModelProperty("商品标题")
    private String title;

    @ApiModelProperty("商品卖点")
    @Column(name="sellpoint",columnDefinition="商品卖点")
    private String sellpoint;

    @ApiModelProperty("商品价格，单位为：分")
    @Column(name="price",columnDefinition="商品价格，单位为：分")
    private long price;

    @ApiModelProperty("库存数量")
    @Column(name="num",columnDefinition="库存数量")
    private int num;

    @ApiModelProperty("商品图片")
    @Column(name="image",columnDefinition="商品图片")
    private String image;

    @ApiModelProperty("所属类目，叶子类目")
    @Column(name="cid",columnDefinition="所属类目，叶子类目")
    private long cid;

    @ApiModelProperty("商品状态，1-正常，2-下架，3-删除")
    @Column(name="status",columnDefinition="商品状态，1-正常，2-下架，3-删除")
    private byte status;
}
