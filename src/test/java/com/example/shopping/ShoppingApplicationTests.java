package com.example.shopping;

import com.example.shopping.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingApplicationTests {
    @Autowired
    CartService cartService;

    @Test
    void contextLoads() {
    }

}
