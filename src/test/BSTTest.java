package test;

import main.tree.BST;

public class BSTTest {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 20; i++) {
            bst.add((int) (1 + Math.random() * 40));
        }
        System.out.println(bst);


        System.out.println("获取书中最小值");
        System.out.println(bst.mininum());
        System.out.println();

        System.out.println("获取树中最大值");
        System.out.println(bst.maximum());
        System.out.println();

        System.out.println("前序遍历");
        bst.preOrder();
        System.out.println();

        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println();

        System.out.println("后序遍历");
        bst.postOrder();
        System.out.println();

        System.out.println("执行移除树中最大元素" + bst.removeMax());
        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println();

        System.out.println("执行移除树中最小元素" + bst.removeMin());
        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println();
    }
}
