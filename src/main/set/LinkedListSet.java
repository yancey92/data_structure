package main.set;

import main.list.SingleLinkedList;

/**
 * 基于链表实现一个Set集合
 *
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {

    private SingleLinkedList data;


    public LinkedListSet() {
        this.data = new SingleLinkedList();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    @Override
    public void add(E e) {
        if (!contains(e)) {
            this.data.addFirst(e); //使用addFirst，因为此方法是O(1)的时间复杂度
        }
    }

    @Override
    public boolean contains(E e) {
        return this.data.contains(e);
    }

    @Override
    public void remove(E e) {
        this.data.removeElement(e);
    }
}
