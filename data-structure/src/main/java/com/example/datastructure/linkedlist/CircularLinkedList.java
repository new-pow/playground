package com.example.datastructure.linkedlist;

import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements LinkedList<T> {

    private final int MAX_SIZE = Integer.MAX_VALUE;

    SingleNode<T> head;
    SingleNode<T> tail;
    int size;

    public CircularLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(Object data) {
        SingleNode<T> temp = head;
        int result = 0;
        while (temp.next != null) { // 시간복잡도 O(n)
            if (temp.data.equals(data)) {
                return result;
            }
            temp = temp.next;
            result ++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        int count = 0;

        SingleNode<T> temp = head;
        while (count!=index) { // 시간복잡도 O(n)
            temp = temp.next;
            count ++;
        }
        return temp.data;
    }

    @Override
    public void add(T data) {
        if (this.size==MAX_SIZE) throw new IllegalArgumentException();

        SingleNode<T> newNode = new SingleNode<>(data);

        if (isEmpty()) {
            this.head = newNode;
        }

        this.tail = newNode;
        this.tail.next = head;
        this.size ++;
    }

    @Override
    public void add(int index, T data) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();

        SingleNode<T> newNode = new SingleNode<>(data);
        SingleNode<T> before = this.tail;
        SingleNode<T> temp = this.head;
        int count = 0;
        while (count != index) {
            before = before.next;
            temp = temp.next;
            count ++;
        }
        before.next = newNode;
        newNode.next = temp;

        if (index==0) this.head = newNode;
        if (index==size-1) this.tail = newNode;

        this.size ++;
    }

    @Override
    public void remove() {
        if (isEmpty()) throw new IllegalArgumentException();

        if (size == 1) {
            this.head = this.tail = null;
            this.size --;
            return;
        }

        SingleNode<T> before = this.tail;
        while (before.next.next != null) {
            before = before.next;
        }
        before.next = null;
        this.tail = before;
        this.size --;
    }

    @Override
    public void remove(int index) {
        if (isEmpty()) throw new IllegalArgumentException();
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();

        SingleNode<T> before = this.tail;
        SingleNode<T> temp = this.head;
        int count = 0;
        while (count!=index) {
            before = before.next;
            temp = temp.next;
            count ++;
        }
        before.next = temp.next;
        this.size --;

        if (index == 0) {
            this.head = temp.next;
        }

        if (index == size-1) {
            this.tail = before;
        }
    }
}
