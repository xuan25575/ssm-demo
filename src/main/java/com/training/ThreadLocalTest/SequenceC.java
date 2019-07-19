package com.training.ThreadLocalTest;


public class SequenceC  implements Sequence{
    private static MyThreadLocal<Integer>  numberContainer = new MyThreadLocal<Integer>(){
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
