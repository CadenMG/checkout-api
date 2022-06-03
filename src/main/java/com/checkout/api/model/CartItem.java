package com.checkout.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@IdClass(CartItemId.class)
public class CartItem implements Serializable {

    @Id
    @Column(nullable = false)
    private Long personId;
    @Id
    @Column(nullable = false)
    private Long itemId;
    @Column(nullable = false)
    private Integer quantity;

    public CartItem() { }

    public CartItem(Long personId, Long itemId, Integer quantity) {
        this.personId = personId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getPersonId() {
        return personId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
