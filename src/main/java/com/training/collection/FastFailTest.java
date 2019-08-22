package com.training.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description TODO
 * @Author huang
 * @date 2019/8/3
 */
public class FastFailTest {

   // private static List<String> list = new ArrayList<String>();
    private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {
        // 同时启动两个线程对list进行操作！
        new T1().start();
        new T2().start();
    }

    private static class T1 extends Thread{
        @Override
        public void run() {
            int i = 10;
            while (i < 16) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }

    private static class T2 extends Thread{
        @Override
        public void run() {
            int i = 0;
            while (i < 6) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }

    public static  void printAll(){
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }

}
