package com.atlavik.service;

import com.atlavik.dao.entity.Product;
import com.atlavik.dao.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProduct(UUID id);

    public Product saveProduct(Product product) ;
}
