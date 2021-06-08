package ru.otus.ageev.services.impl;

import ru.otus.ageev.bean.ProductFilterBean;
import ru.otus.ageev.domain.Product;
import ru.otus.ageev.repository.ProductRepository;
import ru.otus.ageev.repository.transaction.TransactionManager;
import ru.otus.ageev.repository.transaction.impl.TransactionManagerImpl;
import ru.otus.ageev.services.ProductService;
import java.util.List;
import javax.sql.DataSource;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final TransactionManager transactionManager;

    public ProductServiceImpl(ProductRepository productRepository, DataSource dataSource) {
        this.transactionManager = new TransactionManagerImpl(dataSource);
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getByFilter(ProductFilterBean productFilterBean) {
        return transactionManager.executeDqlTransaction(() -> productRepository.getByFilter(productFilterBean));
    }

    @Override
    public Long getCountByFilter(ProductFilterBean productFilterBean) {
        return transactionManager.executeDqlTransaction(() -> productRepository.getCountByFilter(productFilterBean));
    }

    @Override
    public Product getOne(Long id) {
        return transactionManager.executeDqlTransaction(() -> productRepository.getOne(id));
    }

    @Override
    public List<String> getAllProducers() {
        return transactionManager.executeDqlTransaction(productRepository::getAllProducers);
    }

    @Override
    public List<String> getAllCategories() {
        return transactionManager.executeDqlTransaction(productRepository::getAllCategories);
    }
}
