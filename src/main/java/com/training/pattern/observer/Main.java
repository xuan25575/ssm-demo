package com.training.pattern.observer;

public class Main {
    public static void main(String[] args) {
        ProductList observable  = ProductList.getInstance();
        TaoBaoObserver o1 = new TaoBaoObserver();
        JingDongObserver o2 = new JingDongObserver();
        observable.addProductListObserver(o1);
        observable.addProductListObserver(o2);
        observable.addProduct("新增产品1");
        observable.addProduct("新增产品2");

    }
}
