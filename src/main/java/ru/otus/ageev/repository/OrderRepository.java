package ru.otus.ageev.repository;

import ru.otus.ageev.domain.Order;

public interface OrderRepository {
    Order createOrder(Order order);
}
