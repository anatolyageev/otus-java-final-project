package ru.otus.ageev.services.impl;

import ru.otus.ageev.domain.Order;
import ru.otus.ageev.repository.OrderRepository;
import ru.otus.ageev.repository.transaction.TransactionManager;
import ru.otus.ageev.repository.transaction.impl.TransactionManagerImpl;
import ru.otus.ageev.services.OrderService;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    final static Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final TransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository, DataSource dataSource) {
        this.transactionManager = new TransactionManagerImpl(dataSource);
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        LOG.debug("order: " + order);
        return transactionManager.executeDmlTransaction(() -> orderRepository.createOrder(order));
    }
}
