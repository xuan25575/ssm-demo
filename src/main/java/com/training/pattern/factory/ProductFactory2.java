package com.training.pattern.factory;

// 工厂1
public class ProductFactory1 implements IProductFactory {
    @Override
    public Product createProduct(String productNo) {
        Product product = new Product();
        return product;
    }
}
