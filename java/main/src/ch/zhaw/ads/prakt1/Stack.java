package ch.zhaw.ads.prakt1;

public interface Stack {

    void push(Object o);

    Object pop();

    Object peek();

    boolean isEmpty();

    boolean isFull();

    void reset();
}
