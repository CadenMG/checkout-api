package com.checkout.api.service;

import com.checkout.api.model.CartItem;
import com.checkout.api.model.CartItemId;
import com.checkout.api.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void deleteFromCart(Long personId, Long itemId, Integer quantity) {
        CartItemId id = new CartItemId(personId, itemId);
        Integer quantityInCart = cartItemRepository.getById(id).getQuantity();
        if (quantityInCart <= quantity) {
            cartItemRepository.deleteById(id);
        } else {
            cartItemRepository.updateQuantity(personId, itemId, quantity);
        }
    }

    public void deleteAllFromCart(Long personId) {
        cartItemRepository.deleteByPersonId(personId);
    }

    public void addToCart(Long personId, Long itemId, Integer quantity) {
        CartItemId id = new CartItemId(personId, itemId);
        if (cartItemRepository.existsById(id)) {
            Integer quantityInCart = cartItemRepository.getById(id).getQuantity();
            cartItemRepository.updateQuantity(personId, itemId, quantity + quantityInCart);
        } else {
            cartItemRepository.save(new CartItem(personId, itemId, quantity));
        }
    }

    public List<CartItem> getCart(Long personId) {
        return cartItemRepository.findByPersonId(personId);
    }
}
