package ru.otus.ageev.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends LongID {
private OrderStatus orderStatus;
private String statusDetails;
private String requisites;
private LocalDateTime orderDateTime;
private List<ProductsInOrder> userOrder;
private Long userId;


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<ProductsInOrder> getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(List<ProductsInOrder> userOrder) {
        this.userOrder = userOrder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", statusDetails='" + statusDetails + '\'' +
                ", requisites='" + requisites + '\'' +
                ", orderDateTime=" + orderDateTime +
                ", userOrder=" + userOrder +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
