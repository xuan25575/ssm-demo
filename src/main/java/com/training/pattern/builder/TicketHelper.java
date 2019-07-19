package com.training.pattern.builder;

public class TicketHelper {

    public void buildAdult(String info) {
        System.out.println("构建成人票逻辑：" + info);
    }

    public void buildChildrenForSeat(String info) {
        System.out.println("构建有座儿童票逻辑：" + info);
    }

    public void buildChildrenNoSeat(String info) {
        System.out.println("构建无座儿童票逻辑：" + info);
    }

    public  void  buildElderly(String  info)  {
        System.out.println("构建有老人票逻辑：" + info);
    }
    public  void  buildSoldier(String  info)  {
        System.out.println("构建军人及其家属票逻辑：" + info);
    }
}
