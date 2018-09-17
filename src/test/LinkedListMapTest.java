package test;

import main.map.LinkedListMap;
import main.map.Map;

public class LinkedListMapTest {

    public static void main(String[] args) {
        Map map = new LinkedListMap<>();
        for (int i = 0; i < 10; i++) {
            map.add(i, i);
            if (i % 2 == 0) {
                map.remove(i);
            }
        }
        System.out.println(map);
        System.out.println(map.contains(8));
        System.out.println(map.getSize());

    }
}
