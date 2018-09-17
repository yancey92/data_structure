package test;

import main.stack.LinkedListStack;
import main.stack.Stack;

public class LinkedListStackTest {
    
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
