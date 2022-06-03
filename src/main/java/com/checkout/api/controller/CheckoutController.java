package com.checkout.api.controller;

import com.checkout.api.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/checkout/{personId}")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping
    public void checkout(@PathVariable Long personId, String shippingMethod) throws Exception {
        // Verify non-empty cart
        // Insert into purchased table
        // delete from cart table
        checkoutService.checkout(personId, shippingMethod);
    }

}
