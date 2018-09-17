package main.tree;

import main.queue.LoopQueue;
import main.queue.Queue;

/**
 * 二分搜索树
 * ----------          7
 * --------         /     \
 * -----           5       11
 * ------        /  \     /  \
 * ------      2     6   9    12
 * -------    / \       / \
 * -------   1   3     8   10
 * <p>
 * 像这样的二分搜索树，如果有一串数字，是近乎有序的，那么插入到这棵二分搜索树中后，这棵树将会是极度倾斜的，
 * 在极端情况下，二分搜索树将会变成链表
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    //创建一个空的二分树
    public BST() {
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 向二分树中添加元素，首先从root节点开始比对，大的放右边，小的放左边，相等的就返回你添加（也可以自定义规则）
     */
    public void add(E e) {
        if (e == null) {
            return;
        }
        if (root == null) {
            this.root = new Node(e);
            this.size++;
            return;
        }
        add(this.root, e);
    }

    /**
     * 向以node为根的二分搜索树中添加元素
     *
     * @param node
     * @param e
     */
    private void add(Node node, E e) {
        if (e.compareTo(node.e) < 0 && node.left == null) {
            //添加到node的左侧
            node.left = new Node(e);
            this.size++;
            return;
        }
        if (e.compareTo(node.e) > 0 && node.right == null) {
            //添加到node的右侧
            node.right = new Node(e);
            this.size++;
            return;
        }
        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            add(node.right, e);
        }
    }

    public boolean contains(E e) {
        return contains(this.root, e);
    }

    /**
     * 以node为根的二分树是否存在节点e
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {//(e.compareTo(node.e) > 0)
            return contains(node.right, e);
        }
    }

    /**
     * 前序遍历：先遍历父节点，再遍历左孩子，最后遍历右孩子
     */
    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e);
        System.out.print(",");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历：先遍历左孩子，再遍历父节点，最后遍历右孩子
     */
    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e);
        System.out.print(",");
        inOrder(node.right);
    }

    /**
     * 后序遍历：先遍历左孩子，再遍历右孩子，最后遍历父节点
     */
    public void postOrder() {
        postOrder(this.root);
    }

    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e);
        System.out.print(",");
    }

    /**
     * 层序遍历：从左到右，一层一层的遍历；需要借助队列
     */
    public void levelOrder() {
        //从root节点开始入队，每出队一个节点，就将该节点的左右孩子入队
        Queue<Node> queue = new LoopQueue<>();
        if (this.root != null) {
            queue.enqueue(this.root);
        }
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            System.out.println(node.e);
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

    /**
     * 寻找二分搜索树中最小元素，也就是从根节点出发，查找最左侧的节点
     *
     * @return
     */
    public E mininum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return mininum(this.root).e;
    }

    /**
     * 找到以node为根节点中最小的节点元素
     *
     * @param node
     * @return
     */
    private Node mininum(Node node) {
        if (node.left == null) {
            return node;
        }
        return mininum(node.left);
    }

    /**
     * 寻找二分搜索树中最大元素，也就是从根节点出发，查找最右侧的节点
     *
     * @return
     */
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return maxinum(this.root).e;
    }

    /**
     * 找到node节点中最大的节点元素
     *
     * @param node
     * @return
     */
    private Node maxinum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxinum(node.right);
    }

    /**
     * 从二分搜索树中删除最小元素
     *
     * @return
     */
    public E removeMin() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        Node rem = mininum(this.root);
        this.root = removeMin(this.root);
        return rem.e;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            Node nodeRight = node.right;
            node.right = null;
            size--;
            return nodeRight;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最大元素
     *
     * @return
     */
    public E removeMax() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        Node rem = maxinum(this.root);
        this.root = removeMax(this.root);
        return rem.e;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            Node nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除二分搜索树中某一个元素
     *
     * @param e
     */
    public void remove(E e) {
        this.root = remove(this.root, e);
    }

    //从node为根的二分搜索树中移除e之后，返回新的二分搜索树的根
    private Node remove(Node node, E e) {
        //如果移除的是叶子节点直接移除即可
        //如果移除的节点只有左子树，那么直接将左孩子上浮到被移除位置即可
        //如果移除的节点只有右子树，那么需要将右孩子上浮，填补被移除的节点
        //如果被移除的节点即有左子树，也有右子树，那么需要在右子树中找到最小的节点，替换掉移除的节点
        if (node == null) {
            return null;
        }
        if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else { //node.e.compareTo(e) == 0
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //如果要删除的节点的左右子树都不为空，需要找到要删除的节点的右子树中最小的节点替换掉要删除的节点
            Node miniRight = mininum(node.right);

            miniRight.right = removeMin(node.right);
            miniRight.left = node.left;
            node.left = node.right = null;
            return miniRight;
        }
    }


    //二分搜索树打印
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

}
