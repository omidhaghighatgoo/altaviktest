package com.atlavik.service;


import com.atlavik.dao.entity.Product;
import com.atlavik.dao.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProducts() {


        return productRepository.findAll();


    }

    public Product getProduct(UUID id) {

        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("No Product with id " + id + " exists");


    }

    public Product saveProduct(Product product) {

        return productRepository.save(product);


    }
}
