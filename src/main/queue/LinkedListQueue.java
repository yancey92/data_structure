package main.queue;

import main.list.DoubleLinkedList;

/**
 * 使用链表实现队列，一边进一边出，此时单向链表将很难实现，使用双向链表实现
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

    private DoubleLinkedList<E> data;

    public LinkedListQueue() {
        data = new DoubleLinkedList<E>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return data.getFirst();
    }
}
