package com.training.pattern.factory;

// 通用工厂。
public class ProductFactory  implements IProductFactory {
    @Override
    public Product createProduct(String productNo) {
        char ch = productNo.charAt(0);
        IProductFactory factory = null;
        if(ch =='1'){
            factory = new ProductFactory1();
        }else if (ch=='2'){
            factory = new ProductFactory2();
        }else if (ch=='3'){
            factory = new ProductFactory3();
        }
        if(factory != null){
            return factory.createProduct(productNo);
        }
        return null;
    }
}
