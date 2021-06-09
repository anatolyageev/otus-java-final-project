package ru.otus.ageev.services;

import ru.otus.ageev.bean.ProductFilterBean;
import ru.otus.ageev.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getByFilter(ProductFilterBean productFilterBean);

    public Long getCountByFilter(ProductFilterBean productFilterBean);

    Product getOne(Long id);

    List<String> getAllProducers();

    List<String> getAllCategories();

}
