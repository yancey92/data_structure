package main.queue;


import main.list.ArrayList;

/**
 * 使用动态数组实现队列：先进先出原则
 * 数组队列具有局限性，出队操作的时间复杂度是O(N)
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

    private ArrayList<E> data;

    public ArrayQueue(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public ArrayQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return this.data.getSize();
    }

    private int getCapacity() {
        return this.data.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        this.data.addLast(e);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        return this.data.removeFirst();
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return this.data.getFirst();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayQueue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        sb.append("front [");
        for (int i = 0; i < this.getSize(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(this.data.get(i));
        }
        sb.append("] tail");
        return sb.toString();
    }

}
