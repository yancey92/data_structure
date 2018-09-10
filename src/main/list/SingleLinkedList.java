package main.list;

/**
 * 单项链表实现：dummyHead->1->3->5->7->9->11->13->15->17->19->21->23->25->27->29->NULL
 * 需要借助一个虚拟头节点dummyHead，此节点不对外暴露
 *
 * @param <E>
 */
public class SingleLinkedList<E> implements List<E> {

    private class Node {
        E e;
        Node next;//指向下一个元素

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }
    }

    private int size;//链表中元素的个数，不包含虚拟头结点
    private Node dummyHead;//虚拟头结点

    public SingleLinkedList() {
        this.size = 0;
        this.dummyHead = new Node(null);
    }

    //获取链表中元素的个数
    @Override
    public int getSize() {
        return this.size;
    }

    //链表是否为空
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    //向链表首部添加元素
    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    //向链表尾部添加元素
    @Override
    public void addLast(E e) {
        add(this.size, e);
    }

    //向链表中插入元素
    @Override
    public void add(int index, E e) {
        if (!(0 <= index && index <= this.size)) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node tempNode = this.dummyHead;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        tempNode.next = new Node(e, tempNode.next);
        this.size++;
    }

    @Override
    public boolean contains(E e) {
        if (e == null) {
            return false;
        }
        //找到这个元素位置，然后删除
        Node temp = this.dummyHead.next;
        while (temp != null) {
            if (temp.e.equals(e)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    //获取链表中第一个位置的元素
    @Override
    public E getFirst() {
        return get(0);
    }

    //获取链表中最后一个位置的元素
    @Override
    public E getLast() {
        return get(size - 1);
    }

    //获取列表中指定位置的元素
    @Override
    public E get(int index) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("Find failed. Illegal index.");
        }
        Node tempNode = this.dummyHead;
        for (int i = 0; i <= index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.e;
    }

    //移除链表中指定位置的元素
    @Override
    public E remove(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("Do not remove element from a empty List.");
        }
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException(" Illegal index.");
        }
        Node beforeNode = this.dummyHead;
        for (int i = 0; i < index; i++) {
            beforeNode = beforeNode.next;
        }
        Node removeNode = beforeNode.next;
        beforeNode.next = removeNode.next;
        removeNode.next = null;
        this.size--;
        return removeNode.e;
    }

    //移除链表中第一个位置的元素
    @Override
    public E removeFirst() {
        return remove(0);
    }

    //移除链表中最后一个位置的元素
    @Override
    public E removeLast() {
        return remove(this.size - 1);
    }

    //从链表中删除一元素e
    @Override
    public void removeElement(E e) {
        if (e == null) {
            return;
        }
        //找到这个元素位置，然后删除
        Node temp = this.dummyHead;
        while (temp.next != null) {
            if (temp.next.e.equals(e)) {
                //删除temp.next
                Node delNode = temp.next;
                temp.next = delNode.next;
                delNode.next = null;
                this.size--;
                break;
            }
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = this.dummyHead.next;
        while (temp != null) {
            sb.append(temp.e);
            temp = temp.next;
            sb.append("->");

        }
        sb.append("NULL");
        return sb.toString();
    }


    public static void main(String[] args) {
        List<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 30; i++) {
            list.addLast(i);
            if (i % 2 == 0) {
                list.removeElement(i);
            }
        }
        System.out.println(list);
        System.out.println(list.contains(3));

    }
}
