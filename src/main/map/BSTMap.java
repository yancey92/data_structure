package main.map;


/**
 * 使用二分搜索树实现一个[k,v]映射
 *
 * @param <K>
 * @param <V>
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {

    }

    @Override
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The map's key do not allow null");
        }
        this.root = add(this.root, key, value);
    }

    //以node为根的二分搜索树中添加节点，返回新的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {//key.compareTo(node.key)>0
            node.right = add(node.right, key, value);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(this.root, key); //找到要移除的节点
        if (node != null) {
            this.root = remove(this.root, key);
            return node.value;
        } else { //如果没有找到要移除的节点
            return null;
        }
    }

    //从node为根的二分搜索树中移除key节点，并返回新的二分搜索树的根
    private Node remove(Node node, K key) {
        //如果移除的是叶子节点直接移除即可
        //如果移除的节点只有左子树，那么直接将左孩子上浮到被移除位置即可
        //如果移除的节点只有右子树，那么需要将右孩子上浮，填补被移除的节点
        //如果被移除的节点即有左子树，也有右子树，那么需要在右子树中找到最小的节点，替换掉移除的节点
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            return node;
        } else if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            return node;
        } else {//(node.key.compareTo(key) == 0)
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

    //找到node为根的二分搜索树中最小的节点
    private Node mininum(Node node) {
        if (node.left == null) {
            return node;
        }
        return mininum(node.left);
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

    @Override
    public boolean contains(K key) {
        return getNode(this.root, key) != null;
    }

    @Override
    public V get(K key) {
        return getNode(this.root, key).value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(this.root, key);
        node.value = newValue;
    }

    //从node为根的二分搜索树中找key节点
    private Node getNode(Node node, K key) {
        if (key == null) {
            throw new IllegalArgumentException("The map's key do not allow null");
        }
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {//(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
