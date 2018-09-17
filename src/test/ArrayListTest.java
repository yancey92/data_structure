package test;

import main.list.ArrayList;

public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            arrayList.addLast(i);
        }
        System.out.println(arrayList);
    }
}
