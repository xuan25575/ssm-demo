package com.training.ThreadLocalTest;

/**
 *
 * 业务场景  ：  当修改产品价格完后，需要将 日志表中插入一条记录。
 * 可以考虑用   ThreadLcoal
 */
public class SequenceB implements  Sequence {

    private static ThreadLocal<Integer>  numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceB();

        ClientThread c1 = new ClientThread(sequence);
        ClientThread c2 = new ClientThread(sequence);
        ClientThread c3 = new ClientThread(sequence);

        c1.start();
        c2.start();
        c3.start();
    }
}
