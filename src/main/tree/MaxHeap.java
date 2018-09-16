package main.tree;

import main.list.ArrayList;

/**
 * 二叉堆：堆有很多种，常用的是树形结构的二叉堆，这样的堆是一个完全二叉树，数据从上到下从左到右依次排放
 * <p>
 * 对于最大堆而言：堆中某一个节点的值总是 <= 其父亲节点的值；但是层次深的节点不一定就一定小于层次浅的节点
 * 最大堆：使用堆这样的数据结构实现一个优先队列
 * <p>
 * ----------           19
 * --------         /       \
 * -----          17        11
 * ------       /  \       /   \
 * ------      15   16    9     10
 * -------   / \   / \   / \   /  \
 * ------- 14 13  7  3  8  6  5   4
 * <p>
 * 对于以上的二叉堆，可以将元素放入数组中，[19] [17] [11] [15] [16] [9] [10] [14] [13] [7] [3] [8] [6] [5] [4]
 * 那么节点 i 的
 * <p>   父亲是: (index - 1) / 2
 * <p>   左孩子是: index * 2 + 1
 * <p>   右孩子是: index * 2 + 2
 */
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;//使用动态数组存放堆中的元素


    public MaxHeap() {
        data = new ArrayList<E>();
    }

    public MaxHeap(int capacity) {
        data = new ArrayList<E>(capacity);
    }

    public MaxHeap(E[] arr) {
        data = new ArrayList<E>(arr.length);

    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parentIndex(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index:0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChildIndex(int index) {
        return index * 2 + 1;
    }

    // 返回堆中的元素个数
    public int getSize() {
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 向堆中添加元素，将元素添加到二叉堆的最后一个叶子节点，然后上浮
    // 可以插入已经存在的元素
    public void add(E e) {
        this.data.addLast(e);
        siftUp(this.data.getSize() - 1);
    }

    //将二叉堆中某个位置的元素执行上浮操作
    private void siftUp(int index) {
        while (index > 0 && data.get(parentIndex(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    // 看堆中的最大元素，即二叉堆的根节点
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素，将堆中的最小的元素放到根这个位置，然后下沉
    public E extractMax() {
        E e = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return e;
    }

    private void siftDown(int index) {
        // 要下沉的节点和他的左右孩子比较，如果父亲节点分别>=左右孩子，此时不做下沉；
        // 否则找到最大的那个节点和父亲节点交换位置

        while (leftChildIndex(index) < data.getSize()) {
            int j = leftChildIndex(index); // 在此轮循环中,data[k]和data[j]交换位置
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if (data.get(index).compareTo(data.get(j)) >= 0)
                break;

            data.swap(index, j);
            index = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}