package com.training.pattern.observer;

import java.util.Observable;
import java.util.Observer;

// 观察者
public class TaoBaoObserver implements Observer {

    @Override
    public void update(Observable o, Object product) {
        String newProduct = (String)product;
        System.out.println("发送新产品"+newProduct+"到淘宝商城");
    }
}
