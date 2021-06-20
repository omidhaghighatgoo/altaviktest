package com.atlavik.service;

import com.atlavik.dao.entity.Product;
import com.atlavik.dao.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ShoppingCartService {

    public List<ShoppingCart> getAllCarts ();

    public ShoppingCart getCart(UUID id);

    public ShoppingCart saveCart(ShoppingCart shoppingCart) ;




}
