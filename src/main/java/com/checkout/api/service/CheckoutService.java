package com.checkout.api.service;

import com.checkout.api.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private final CartService cartService;

    public CheckoutService(CartService cartService) {
        this.cartService = cartService;
    }

    public void checkout(Long personId, String shippingMethod) throws Exception {
        List<CartItem> items = cartService.getCart(personId);
        if (items.isEmpty()) {
            throw new Exception("Cannot checkout with empty cart");
        }
        // todo: insert into purchased table
        cartService.deleteAllFromCart(personId);
    }
}
