package main.list;

/**
 * 双向链表：dummyHead <==> 0 <==> 1 <==> 2 <==> 3 <==> 4 <==> 5
 * 需要借助一个虚拟头节点dummyHead，此节点不对外暴露
 *
 * @param <E>
 */
public class DoubleLinkedList<E> implements List<E> {

    private class Node {
        E e;
        Node next;//指向下一个元素
        Node previous;//指向上一个元素

        public Node(E e, Node previous, Node next) {
            this.e = e;
            this.previous = previous;
            this.next = next;
        }

        public Node(E e) {
            this(e, null, null);
        }
    }

    private int size;//链表中元素的个数，不包含虚拟头结点
    private Node dummyHead;//虚拟头结点
    private Node tail;//尾结点

    public DoubleLinkedList() {
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
        Node previous = this.dummyHead;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        Node carryOn = previous.next;
        //previous为要插入index位置的前驱,previous的后继此时应该成为addNode的后继
        Node addNode = new Node(e, previous, carryOn);
        previous.next = addNode;
        //插入节点此时要变成之前该位置节点的前驱
        if (carryOn != null)
            carryOn.previous = addNode;
        this.size++;
        if (this.size == 0 || index == this.size - 1) {
            this.tail = addNode;
        }
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
        if (index == this.size - 1) {
            return this.tail.e;
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
        Node previous = this.dummyHead;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        //previous为要移除index位置的前驱
        //1--2(delete)--3
        Node removeNode = previous.next;
        Node carryOn = removeNode.next;//后继
        previous.next = carryOn;
        if (carryOn != null)
            carryOn.previous = previous;

        removeNode.next = null;
        removeNode.previous = null;
        this.size--;
        //如果影响到了tail就做相应调整
        if (index == size - 1 && index == 0) {
            this.tail = null;
        } else if (index == size - 1) {
            this.tail = previous;
        }
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
        Node temp = this.dummyHead.next;
        while (temp != null) {
            if (temp.e.equals(e)) {
                // 维护一下tail结点
                if (temp.equals(tail) && temp.previous.equals(this.dummyHead)) {
                    this.tail = null;
                } else if (temp.next == null) { //如果移除的结点的下一个结点是null，说明移除的结点是尾结点
                    this.tail = temp.previous;
                }
                //删除元素
                Node removeNode = temp;
                Node previous = removeNode.previous;
                Node carryOn = removeNode.next;

                previous.next = carryOn;
                if (carryOn != null)
                    carryOn.previous = previous;
                removeNode.previous = null;
                removeNode.next = null;
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
//            System.out.print(temp.previous.e);
//            System.out.print(temp.e);
//            if (temp.next != null) {
//                System.out.print(temp.next.e);
//            } else {
//                System.out.print("NULL");
//            }
//            System.out.println("\n");
            sb.append(temp.e);
            temp = temp.next;
            sb.append("<->");

        }
        sb.append("NULL");
        return sb.toString();
    }


    public static void main(String[] args) {
        List<Integer> list = new DoubleLinkedList<>();
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
