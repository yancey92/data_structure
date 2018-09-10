package main.stack;

import main.list.DoubleLinkedList;

/**
 * 由于对链表的头部添加和删除都会O(1)复杂度，栈的操作只在一端，所以可以用链表实现一个Stack，栈顶在链表的头部
 * 如果用链表实现一个队列，那么肯定有一端是O(n)复杂度
 *
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {

    private DoubleLinkedList<E> data;

    public LinkedListStack() {
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
    public void push(E e) {
        data.addFirst(e);
    }

    @Override
    public E pop() {
        return data.removeFirst();
    }

    @Override
    public E peek() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(data);
        return res.toString();
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

}
