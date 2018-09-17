package test;

import main.list.DoubleLinkedList;
import main.list.List;
import main.list.SingleLinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        List<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 30; i++) {
            list.addLast(i);
            if (i % 2 == 0) {
                list.removeElement(i);
            }
        }
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        
        list = new DoubleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.addLast(i);
            if (i % 3 == 0) {
                list.removeElement(i);
            }
        }
        System.out.println(list);
        System.out.println(list.getLast());
    }
}
