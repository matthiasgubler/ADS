package ch.zhaw.ads.prakt1;

public class ArrayStack implements Stack {

    private int capacity;

    private Object[] buffer;

    private int top;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        reset();
    }

    public void reset() {
        top = 0;
        buffer = new Object[capacity];
    }

    @Override
    public void push(Object o) throws StackOverflowError {
        if (isFull()) {
            throw new StackOverflowError();
        }

        buffer[top] = o;
        top++;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            return null;
        }

        top--;
        Object popResult = buffer[top];
        buffer[top] = null;

        return popResult;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return buffer[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == capacity;
    }
}
