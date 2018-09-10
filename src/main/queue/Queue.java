package main.queue;

/**
 * 定义队列接口
 *
 * @author yangxinxin_mail@163.com
 */
public interface Queue<E> {
    //获取队中的元素数量
    int getSize();

    //判断队列是否为空
    boolean isEmpty();

    //入队操作
    void enqueue(E e);

    //出队操作
    E dequeue();

    //获取队列中第一个元素（队首的元素）
    E getFront();
}
