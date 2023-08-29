package com.example.datastructure.stack;

import java.util.EmptyStackException;

public class Stack<E> implements Cloneable{
    private int top;
    private E[] datas;
    private int capacity;

    private Stack(int top, E[] datas, int capacity) {
        this.top = top;
        this.datas = datas;
        this.capacity = capacity;
    }

    public Stack(int capacity) {
        this.top = 0;
        this.capacity = capacity;
        this.datas = (E[]) new Object[capacity];
    }

    public void push(E data) {
        this.datas[top] = data;
        if (top >= capacity) {
            throw new StackOverflowError();
        }
    }

    public E pop() {
        top --;
        if (top < 0) {
            throw new EmptyStackException();
        }
        E data = this.datas[top];
        this.datas[top] = null;

        return data;
    }

    public int size() {
        return top;
    }

    public E peek() {
        return this.datas[top - 1]; // 복사해서 내보내야 하지 않을까?
    }

    public boolean isEmpty() {
        return this.top <= 0;
    }

    public boolean isFull() {
        return this.top == capacity - 1;
    }
}
