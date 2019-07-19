package com.training.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
 // 被观察者
public class ProductList extends Observable {

    private List<String> productList = null;  // 产品列表

    private static ProductList  instance = null;
    private ProductList(){}

    public static ProductList getInstance(){
        if(instance == null){
            instance = new ProductList();
            instance.productList = new ArrayList<String>();
        }
        return instance;
    }


    public void addProductListObserver(Observer observer){
        this.addObserver(observer);
    }


    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增了产品："+newProduct);
        this.setChanged(); // 设置观察者对象发生了变化。
        this.notifyObservers(newProduct); //通知观察这 并传递对象
    }

}
