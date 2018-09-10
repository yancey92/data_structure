package main.list;

/**
 * 动态数组，可伸缩
 * 数组中元素对应的索引：[0][1][2][3][4][5]
 * 数组中的元素排放：   [a][b][c][d][][][]
 *
 * @param <E>
 * @author yangxinxin_mail@163.com
 */
public class ArrayList<E> implements List<E> {

    private E[] data; //存放数据
    private int size; //实际的数据个数

    //构造一个数组，根据传入的capacity
    public ArrayList(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    //默认的构造方法，如果没有指定容量
    public ArrayList() {
        this(10);
    }

    //获取数组的容量
    public int getCapacity() {
        return this.data.length;
    }

    //获取数组元素的个数
    @Override
    public int getSize() {
        return this.size;
    }

    //判断数组是否为空
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //获取index索引位置的元素
    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Find failed. Require index >= 0 and index < size.");
        }
        return data[index];
    }

    //获取第一个元素
    @Override
    public E getFirst() {
        return get(0);
    }

    //获取最后一个元素
    @Override
    public E getLast() {
        return get(this.size - 1);
    }

    //修改index索引位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Find failed. Require index >= 0 and index < size.");
        }
        data[index] = e;
    }

    //数组中是否包含元素e
    public boolean contains(E e) {
        for (int i = 0; i < this.size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    //查找元素e所在的数组中的索引，没有找到返回-1
    public int find(E e) {
        for (int i = 0; i < this.size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    //在index索引的位置插入一个新元素e，size++
    @Override
    public void add(int index, E e) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        //如果数组没有放满，就将index后的元素依次后移一位；
        //如果数组已经放满，将数组容量扩大2倍，并对index位置赋值
        if (this.size < this.data.length) {
            for (int i = this.size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
        } else {
            resize(2 * data.length);
        }
        data[index] = e;
        this.size++;
    }


    //将元素插入数组的头部
    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    //将元素插入数组的尾部
    @Override
    public void addLast(E e) {
        add(this.size, e);
    }


    //删除数组中的一个元素
    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Remove failed. Require index >= 0 and index < size.");
        }
        E e = data[index];
        //将index后面的元素依次向前移动一位，最后一位置空（其实不管也可以的，size限制了外围访问范围），size--
        for (int i = index; i < this.size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[this.size - 1] = null;
        size--;
        //如果当前size是data.length的1/4，对data缩容（缩1/2）
        if (this.size == data.length / 4 && data.length >= 2) {
            resize(data.length / 2);
        }
        return e;
    }

    //删除数组中的第一个元素
    @Override
    public E removeFirst() {
        return remove(0);
    }

    //删除数组中的最后一个元素
    @Override
    public E removeLast() {
        return remove(this.size - 1);
    }

    @Override
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    //修改数组的容量
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d , capacity = %d\n", this.size, this.data.length));
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            arrayList.addLast(i);
        }
        System.out.println(arrayList);
    }
}
