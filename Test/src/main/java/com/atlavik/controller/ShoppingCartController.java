package com.atlavik.controller;


import com.atlavik.dao.entity.Product;
import com.atlavik.dao.entity.ShoppingCart;
import com.atlavik.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {


    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/carts")
  public  ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {

        List<ShoppingCart> shoppingCarts =  shoppingCartService.getAllCarts();
        return ResponseEntity.ok(shoppingCarts);


    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable("id") UUID id) {

        ShoppingCart shoppingCart =  shoppingCartService.getCart(id);
        return ResponseEntity.ok(shoppingCart);


    }

    @PostMapping("/carts")
    public ResponseEntity<ShoppingCart> addShoppingCart(@RequestBody ShoppingCart shoppingCart) {

        ShoppingCart responseShoppingCart =  shoppingCartService.saveCart(shoppingCart);
        return ResponseEntity.ok(responseShoppingCart);


    }


    @GetMapping("/carts/{id}/products")
    public ResponseEntity<List<Product>> getProductsInShoppingCart(@PathVariable("id") UUID id) {

        ShoppingCart shoppingCart =  shoppingCartService.getCart(id);
        List<Product> products = (List<Product>) shoppingCart.getProducts();

        return ResponseEntity.ok(products);


    }

    @GetMapping("/carts/{cartId}/products/{shoppingId}")
    public ResponseEntity<Product> getProductInShoppingCart(@PathVariable("cartId") UUID cartId , @PathVariable("productId") UUID productId) {

        ShoppingCart shoppingCart =  shoppingCartService.getCart(cartId);
        Product product = (Product) shoppingCart.getProducts().
                stream().filter(p -> p.getId().equals(productId)).collect(Collectors.toList()).get(0);

        return ResponseEntity.ok(product);


    }

    @PutMapping("/carts/{id}/products")
    public ResponseEntity<String> saveProductsInShoppingCart(@PathVariable("id") UUID id , @RequestBody Product product) {

        ShoppingCart shoppingCart = shoppingCartService.getCart(id);

        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        shoppingCart.setProducts(productSet);
        shoppingCartService.saveCart(shoppingCart);


        return ResponseEntity.ok("ok");


    }

    @DeleteMapping("/carts/{id}/products")
    public ResponseEntity<String> deleteProductsInShoppingCart(@PathVariable("id") UUID id, @RequestBody Product product) {

        ShoppingCart shoppingCart = shoppingCartService.getCart(id);

        Set<Product> productSet = new HashSet<>();
        productSet = shoppingCart.getProducts();
        productSet.remove(product);

        shoppingCart.setProducts(productSet);
        shoppingCartService.saveCart(shoppingCart);


        return ResponseEntity.ok("ok");


    }


}
