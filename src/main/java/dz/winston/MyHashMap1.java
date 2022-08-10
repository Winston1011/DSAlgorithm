package dz.winston;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: EpochDZ
 * @Date: 14:36 2022/8/10
 * @Description:
 * @Version v1.0
 */
public class MyHashMap1<K, V> {
    // 底层存储链表的数组table
    private MyListMap<K, V>[] table;
    // 哈希表中存入的键值对(entry)个数
    private int size;
    // 初始化默认table容量大小
    private static final int DEFAULT_CAPACITY = 16;
    // 负载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public MyHashMap1() {
        this(DEFAULT_CAPACITY);
    }

    // 构造函数
    public MyHashMap1(int capacity) {
        table = (MyListMap<K, V>[]) new MyListMap[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new MyListMap<>();
        }
        this.size = 0;
    }

    // 增或改
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // 扩容
        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            reSize(table.length * 2);
        }
        MyListMap<K, V> slot = table[hash(key)];
        // 如果 key 之前不存在，则插入，size 增加
        if (!slot.containsKey(key)) {
            size++;
        }
        return slot.put(key, val);
    }

    // 查
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        MyListMap<K, V> slot = table[hash(key)];
        return slot.get(key);
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        MyListMap<K, V> slot = table[hash(key)];
        return slot.containsKey(key);
    }

    // 删
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        MyListMap<K, V> slot = table[hash(key)];
        // 如果 key 之前存在，则删除，size 减少
        if (slot.containsKey(key)) {
            size--;
            return slot.remove(key);
        }
        return null;
    }

    // 其他函数
    private boolean isEmpty() {
        return size == 0;
    }

    private int getSize() {
        return size;
    }

    private int hash(K key) {
        // 保证非负数
        return (key.hashCode() & 0x7fffffff) % (table.length);
    }

    private void reSize(int newCap) {
        // 构造一个更大容量的 HashMap
        MyHashMap1<K, V> newMap = new MyHashMap1<>(newCap);
        // 穷举当前 HashMap 中的所有键值对
        for (MyListMap<K, V> slot : table) {
            for (Map.Entry<K, V> entry : slot.entries()) {
                K key = entry.getKey();
                V val = entry.getValue();
                // 将键值对转移到新的 HashMap 中
                newMap.put(key, val);
            }
        }
        // 将当前 HashMap 的底层 table 换掉
        this.table = newMap.table;
    }

}
