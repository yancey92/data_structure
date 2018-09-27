package main.hash;

import java.util.TreeMap;

/**
 * 哈希表充分体现了算法设计领域中的经典思想：空间换时间
 * <p>
 * 哈希表实际上就是一个数组，这个数组的大小就是取模的那个素数M，通常每一个位置存储的是一个具体的查找表（可以是一个链表也可以是一个树结构）
 * 对于java语言来说，java8之前hash表的每一个索引位置对应的是一个链表，
 * java8开始当hash冲突达到一定程度后，java8会将这样的链表转换成红黑树
 * <p>
 * 一致性：如果a==b，则hash（a）=hash（b）；反过来不一定成立，即哈希冲突
 * 高效性：计算高效便捷
 * 均匀性：哈希值分布均匀
 * <p>
 * 解决哈希重复的的常用方法是使用：链地址法（Seperate Chanining）
 */
public class HashTable<K extends Comparable<K>, V> {

    //定义hash表的容量，由于计算完的hash值需要取模（一个素数），不同的hash范围，为了均匀分布哈希值，取模的值不同
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    //平均的哈希冲突临界值（上限）
    private static final int upperTol = 10;

    //平均的哈希冲突临界值（下限）
    private static final int lowerTol = 2;

    //使用的取模
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable() {
        this.M = this.capacity[this.capacityIndex];
        this.size = 0;
        hashtable = new TreeMap[this.M];
        for (int i = 0; i < this.M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        //得到一个数的绝对值
        //return Math.abs(key.hashCode()); //如果对integer的最小值取绝对值，依旧返回这个最小值
        Integer hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            hashCode = hashCode - 1;
        } else if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % this.M;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");

        map.put(key, value);
    }

    public void add(K key, V value) {
        //首先计算key的hash值对应的数组索引，将key--value放到对应的数组索引指向的空间
        TreeMap map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            this.size++;
            if (this.size >= upperTol * this.M && capacityIndex < capacity.length - 1) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        V value = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            value = map.remove(key);
            this.size--;
            if (this.size < lowerTol * this.M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return value;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newhashtable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newhashtable[i] = new TreeMap<>();
        }
        int oldM = this.M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = this.hashtable[i];
            for (K key : map.keySet()) {
                newhashtable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newhashtable;
    }

}
