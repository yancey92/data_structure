package main.list;

public interface List<E> {

    int getSize();

    boolean isEmpty();

    void addFirst(E e);

    void addLast(E e);

    void add(int index, E e);

    boolean contains(E e);

    E getFirst();

    E getLast();

    E get(int index);

    E remove(int index);

    E removeFirst();

    E removeLast();

    void removeElement(E e);

}
