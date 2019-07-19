package com.training.ThreadLocalTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {

    private Map<Thread,T>  map = Collections.synchronizedMap(new HashMap<Thread, T>());

    protected T initialValue() {
        return null;
    }


    public  T get(){
        Thread  thread = Thread.currentThread();
        T  value = map.get(thread);
        if(value == null && !map.containsKey(thread)){
            value = initialValue();
            map.put(thread,value);
        }
        return value;
    }

    public void set(T value){
        map.put(Thread.currentThread(),value);
    }

    public void remove(){
        map.remove(Thread.currentThread());
    }
}
