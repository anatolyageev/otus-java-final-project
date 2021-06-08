package ru.otus.ageev.domain;

import java.math.BigDecimal;

public final class ProductsInOrder {
    private Long productId;
    private Long orderId;
    private BigDecimal productPrice;
    private int numberInOrder;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getNumberInOrder() {
        return numberInOrder;
    }

    public void setNumberInOrder(int numberInOrder) {
        this.numberInOrder = numberInOrder;
    }
}
