package main.set;

import main.tree.BST;

/**
 * 基于二分搜索树实现一个Set集合
 * 由于使用的二分搜索树BST本身在设计的时候不会出现重复元素出现，所以直接拿来使用
 *
 * @param <E>
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> data;

    public BSTSet() {
        this.data = new BST<E>();
    }

    @Override
    public int getSize() {
        return this.data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void add(E e) {
        this.data.add(e);
    }

    @Override
    public boolean contains(E e) {
        return this.data.contains(e);
    }

    @Override
    public void remove(E e) {
        this.data.remove(e);
    }
}
