package ru.otus.ageev.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Cart {
    private final Map<Product, Integer> cartStorage;

    public Cart() {
        cartStorage = new HashMap<>();
    }

    public Product addProductToCart(Product product, int amount) {
        cartStorage.put(product, amount);
        return product;
    }

    public void deleteProduct(Product product) {
        cartStorage.remove(product);
    }

    public Set<Product> getProductsList() {
        return cartStorage.keySet();
    }

    public List<ProductsInOrder> getProductsInOrder() {
        List<ProductsInOrder> productsInOrderList = new ArrayList<>();
        cartStorage.forEach((key, value) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setProductPrice(key.getPrice());
            productsInOrder.setProductId(key.getId());
            productsInOrder.setNumberInOrder(value);
            productsInOrderList.add(productsInOrder);
        });
        return productsInOrderList;
    }

    public Integer getProductsNumber() {
        return cartStorage.values().stream().reduce(0, Integer::sum);
    }

    public BigDecimal getTotalPrice() {
        return cartStorage.entrySet()
                .stream()
                .map(es -> es.getKey().getPrice().multiply(new BigDecimal(es.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isProductPresent(Product product) {
        return cartStorage.containsKey(product);
    }

    public Integer getAmountOfProduct(Product product) {
        return cartStorage.get(product);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartStorage=" + cartStorage.toString() +
                '}';
    }
}
