package com.checkout.api.repository;

import com.checkout.api.model.CartItem;
import com.checkout.api.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    @Query(value = "select c.* from cart_item c where c.person_id = ?1", nativeQuery = true)
    List<CartItem> findByPersonId(Long personId);

    @Transactional
    @Modifying
    @Query(value = "update cart_item set quantity = ?3 where person_id = ?1 and item_id = ?2", nativeQuery = true)
    void updateQuantity(Long personId, Long itemId, Integer quantity);

    @Transactional
    @Modifying
    @Query(value = "delete from cart_item where person_id = ?1", nativeQuery = true)
    void deleteByPersonId(Long personId);
}
