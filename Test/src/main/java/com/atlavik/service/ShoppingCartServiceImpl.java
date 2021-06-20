package com.atlavik.service;


import com.atlavik.dao.entity.Product;
import com.atlavik.dao.entity.ShoppingCart;
import com.atlavik.dao.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    @Autowired
    ShoppingCartRepository shoppingCartRepository ;


    public List<ShoppingCart> getAllCarts (){


          return  shoppingCartRepository.findAll();


    }

    public ShoppingCart getCart(UUID id) {

        Optional<ShoppingCart> optional = shoppingCartRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("No Shopping Cart with id " + id + " exists");




    }

    public ShoppingCart saveCart(ShoppingCart shoppingCart) {

        return shoppingCartRepository.save(shoppingCart);

    }


}
