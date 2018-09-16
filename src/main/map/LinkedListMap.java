package main.map;


/**
 * 使用单向链表实现[K，V]映射
 *
 * @param <K>
 * @param <V>
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node next;//指向下一个元素

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node(K key) {
            this(key, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private int size;//链表中元素的个数
    private Node dummyHead;//虚拟头结点

    public LinkedListMap() {
        this.size = 0;
        this.dummyHead = new Node(null, null, null);
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            this.dummyHead.next = new Node(key, value, this.dummyHead.next);
            this.size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node previous = this.dummyHead;
        while (previous.next != null) {
            //找到要移除的节点的前驱
            if (previous.next.key.equals(key))
                break;
            else
                previous = previous.next;
        }
        if (previous.next.key.equals(key)) {
            Node removeNode = previous.next;
            previous.next = removeNode.next;
            removeNode.next = null;
            this.size--;
            return removeNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        return node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = this.dummyHead.next;
        while (temp != null) {
            sb.append(temp);
            temp = temp.next;
            sb.append("->");

        }
        sb.append("NULL");
        return sb.toString();
    }


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
