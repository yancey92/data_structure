package main.stack;

import main.list.ArrayList;

/**
 * 使用动态数组实现栈模型：后进先出原则
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private ArrayList<E> data;

    public ArrayStack(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public ArrayStack() {
        data = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public void push(E e) {
        this.data.addLast(e);
    }

    @Override
    public E pop() {
        return this.data.removeLast();
    }

    @Override
    public E peek() {
        return this.data.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < data.getSize(); i++) {
            res.append(data.get(i));
            if (i != data.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
