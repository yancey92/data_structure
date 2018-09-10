package main.queue;

/**
 * 循环队列实现：先进先出原则；循环队列中必须要保证至少有一个空位,tail始终指向空位
 * 队列为空的时候:front==tail
 * []    []    []    []    []    []    []
 * <p>
 * 队列满的时候：
 * [0]    [1]    [2]    [3]    [4]    [5]    []
 * front                                     tail
 * <p>
 * [0]    [1]    []    [3]    [4]    [5]    [6]
 * --------------tail  front
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;//队首所在的索引
    private int tail;//队尾所在的索引

    //创建一个具有初始容量的数组
    public LoopQueue(int capacity) {
        //对于用户来说队列的容量是capacity，对于实现来说，要增加一个空位
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        if (tail == front) { //当tail == front，有且只有一种情况：tail == front == 0
            return 0;
        }
        //tail 和 front的范围是[0,data.length-1],在这个范围内按照顺时针方向求front 与 tail之间的元素个数
        if (front < tail) {
            return tail - front;
        } else {
            return data.length - front + tail;
        }
    }

    private int getCapacity() {
        return this.data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void enqueue(E e) {
        //如果队列没有满，就入队，入队的位置是tail所在的位置，并偏移tail的索引位置
        if (getSize() < getCapacity()) {
            data[tail] = e;
            if (tail == data.length - 1) {//如果tail指向数组最后一个位置，就偏移tail到数组头部
                tail = 0;
            } else {
                tail++;
            }
        } else {
            //如果队列满了，就对数组扩容，将老的队列数据放到新的数组里去，并重新定义front，tail指向的索引
            resize(getCapacity() * 2 + 1);
            data[tail] = e;
            tail++;
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E e = getFront();
        //将出队位置的元素置null，偏移front的位置，如果front此时和tail重合，那么等价于队列为空
        //出队完成后，如果队列中剩余元素个数达到容量的1/4就缩容
        this.data[front] = null;
        if (front < this.data.length - 1) {
            front++;
        } else {
            front = 0;
        }
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2 + 1);
        }
        return e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    //修改数组的容量(扩容或缩容)
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        int tempTail = 0;
        for (int i = front; i != tail; tempTail++) {
            newData[tempTail] = this.data[i];
            if (i == data.length - 1) { //如果遍历到数组的末尾，下一次循环将指向数组的头部
                i = 0;
            } else {
                i++;
            }
        }
        data = newData;
        front = 0;
        tail = tempTail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        sb.append("front [");
        for (int i = front; i != tail; ) {
            sb.append(this.data[i]);
            if (i == data.length - 1) { //如果遍历到数组的末尾，下一次循环将指向数组的头部
                i = 0;
            } else {
                i++;
            }
            if (i != tail) {
                sb.append(",");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
