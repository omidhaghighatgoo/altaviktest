package com.atlavik;

import com.atlavik.controller.ShoppingCartController;
import com.atlavik.dao.entity.Product;
import com.atlavik.dao.entity.ShoppingCart;
import com.atlavik.dao.repository.ProductRepository;
import com.atlavik.service.ProductService;
import com.atlavik.service.ShoppingCartService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AtlavikTestApplicationTests {


    @Autowired
    ShoppingCartService shoppingCartService ;

    @Autowired
    ShoppingCartController shoppingCartController;

    @Autowired
    ProductService productService;



    @Test
    @Order(1)
    void saveCartTest() {

        Set<Product> products = new HashSet<>();
        products.add(new Product(
                "Apple iPhone 12","ELECTRONICS",new BigDecimal(7325.05),LocalDateTime.now().toString(),""
        ));
        ShoppingCart shoppingCart = new ShoppingCart("US","USD", LocalDateTime.now().toString(),"",products);
       ShoppingCart shoppingCartTest = shoppingCartService.saveCart(shoppingCart);
        assert(shoppingCartTest.getId()) != null;


    }




    @Test
    @Order(2)
    void getAllCartsTest() {

        List<ShoppingCart> shoppingCartList = shoppingCartService.getAllCarts() ;
        assertTrue(shoppingCartList.size() > 0) ;

    }


    @Test
    @Order(3)
    void getCart() {

       List<ShoppingCart> shoppingCartList = shoppingCartService.getAllCarts() ;
       ShoppingCart shoppingCart = shoppingCartService.getCart(shoppingCartList.get(0).getId());
       assertNotNull(shoppingCart);


    }

    @Test
    @Order(4)
    void getProductOfCart() {

        ResponseEntity<List<ShoppingCart>> shoppingCartResponse = shoppingCartController.getAllShoppingCarts();
        List<ShoppingCart> shoppingCartList =  shoppingCartResponse.getBody();
        Set<Product> productSet =  shoppingCartList.get(0).getProducts();

        ResponseEntity<Product> product = shoppingCartController.getProductInShoppingCart(shoppingCartList.get(0).getId() ,
              productSet.iterator().next().getId() );
        assertNotNull(product);



    }

    @Test
    @Order(5)
    void addProductToShoppingCard() {



        Product product = productService.saveProduct(new Product(
                "new iPhone 12","ELECTRONICS",new BigDecimal(1000.00),LocalDateTime.now().toString(),""));
        ResponseEntity<List<ShoppingCart>> shoppingCartResponse = shoppingCartController.getAllShoppingCarts();
        List<ShoppingCart> shoppingCartList =  shoppingCartResponse.getBody();

       ResponseEntity<String> response = shoppingCartController.saveProductsInShoppingCart(shoppingCartList.get(0).getId() , product);

        assertTrue(response.getBody().equals("ok"));


    }

    @Test
    @Order(6)
    void deleteProductFromShoppingCard() {



        Product product = productService.saveProduct(new Product(
                "new iPhone 12","ELECTRONICS",new BigDecimal(1000.00),LocalDateTime.now().toString(),""));
        ResponseEntity<List<ShoppingCart>> shoppingCartResponse = shoppingCartController.getAllShoppingCarts();
        List<ShoppingCart> shoppingCartList =  shoppingCartResponse.getBody();

       ResponseEntity<String> response = shoppingCartController.deleteProductsInShoppingCart(shoppingCartList.get(0).getId() , product);

        assertTrue(response.getBody().equals("ok"));


    }



}
