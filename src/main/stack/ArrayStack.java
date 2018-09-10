package main.stack;

import main.list.ArrayList;

/**
 * 使用动态数组实现栈模型：后进先出原则
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private ArrayList<E> arrayList;

    public ArrayStack(int capacity) {
        arrayList = new ArrayList<>(capacity);
    }

    public ArrayStack() {
        arrayList = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.arrayList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.arrayList.isEmpty();
    }

    @Override
    public void push(E e) {
        this.arrayList.addLast(e);
    }

    @Override
    public E pop() {
        return this.arrayList.removeLast();
    }

    @Override
    public E peek() {
        return this.arrayList.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < arrayList.getSize(); i++) {
            res.append(arrayList.get(i));
            if (i != arrayList.getSize() - 1)
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
