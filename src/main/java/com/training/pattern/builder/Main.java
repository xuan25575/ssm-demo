package com.training.pattern.builder;

public class Main {
    public static void main(String[] args) {
        TicketHelper ticketHelper = new TicketHelper();
        ticketHelper.buildAdult("成人票");
        ticketHelper.buildChildrenForSeat("有座儿童");
        ticketHelper.buildChildrenNoSeat("无座儿童");
        ticketHelper.buildElderly("老人票");
        ticketHelper.buildSoldier("军人票");
        Object builder = TicketBuilder.builder(ticketHelper);
    }
}
