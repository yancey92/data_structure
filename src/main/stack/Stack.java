package main.stack;

/**
 * 定义栈接口
 *
 * @author yangxinxin_mail@163.com
 */
public interface Stack<E> {
    //获取栈中的元素数量
    int getSize();

    //判断栈是否为空
    boolean isEmpty();

    //向栈中压如元素
    void push(E e);

    //向栈中拽出一个元素
    E pop();

    //看一眼栈顶的元素
    E peek();
}
