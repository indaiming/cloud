package com.provider5552.service;

import com.provider5552.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

//@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public interface ProductService extends JpaRepository<Product,Long>,Serializable {

}
