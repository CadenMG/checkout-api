package com.checkout.api.model;

import java.io.Serializable;

public class CartItemId implements Serializable {
    private Long personId;
    private Long itemId;

    public CartItemId() { }

    public CartItemId(Long personId, Long itemId) {
        this.personId = personId;
        this.itemId = itemId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
