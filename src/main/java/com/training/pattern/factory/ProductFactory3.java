package com.training.pattern.factory;

// 工厂1
public class ProductFactory3 implements IProductFactory {
    @Override
    public Product createProduct(String productNo) {
        Product product = new Product();
        return product;
    }
}
