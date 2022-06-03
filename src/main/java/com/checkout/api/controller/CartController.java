package com.checkout.api.controller;

import com.checkout.api.model.CartItem;
import com.checkout.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart/{personId}")
public class CartController {

    @Autowired
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItem> getCart(@PathVariable Long personId) {
        return cartService.getCart(personId);
    }

    @DeleteMapping
    public void deleteFromCart(@PathVariable Long personId, Long itemId, Integer quantity) {
        // could verify item exists...
        cartService.deleteFromCart(personId, itemId, quantity);
    }

    @PostMapping
    public void addToCart(@PathVariable Long personId, Long itemId, Integer quantity) {
        // could verify item exists...
        cartService.addToCart(personId, itemId, quantity);
    }
}
