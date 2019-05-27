package com.provider5552.service;

import com.provider5552.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProductService extends JpaRepository<Product,Long>,Serializable {
}
