package dz.winston;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: EpochDZ
 * @Date: 13:00 2022/8/10
 * @Description: 单链表实现哈希槽
 * @Version v1.0
 */
public class MyListMap<K, V> {
    // 单链表map节点
    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V val;
        Node<K, V> next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.val;
        }

        @Override
        public V setValue(V value) {
            V oldVal = this.val;
            this.val = value;
            return oldVal;
        }
    }

    // 虚拟头节点、尾节点
    private final Node<K, V> head, tail;
    // 当前元素大小
    private int size;

    // 构造函数
    public MyListMap() {
        //  初始化头、尾节点
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        // 将头结点和尾结点相连
        head.next = tail;
        this.size = 0;
    }

    // 增或改
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        Node<K, V> temp = getNode(key);
        // 改值
        if (temp != null) {
            return temp.setValue(val);
        }
        // 在头新增
        Node<K, V> x = new Node<>(key, val);
        Node<K, V> temp2 = head.next;
        head.next = x;
        x.next = temp2;
        size++;
        return null;
    }

    // 删
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        Node<K, V> prev = head;
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            if (p.key.equals(key)) {
                prev.next = p.next;
                size--;
                return p.val;
            }
            prev = p;
        }
        return null;
    }

    // 查
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        Node<K, V> ret = getNode(key);
        if (ret != null) {
            return ret.val;
        }
        return null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        Node<K, V> temp = getNode(key);
        return temp != null;
    }

    // 根据key获取Node
    private Node<K, V> getNode(K key) {
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            if (p.key.equals(key)) {
                return p;
            }
        }
        return null;
    }

    // 其他函数
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 返回map所有的键
    public List<K> keys() {
        LinkedList<K> keyList = new LinkedList<>();

        Node<K, V> p = head.next;
        while (p != tail) {
            keyList.addLast(p.key);
            p = p.next;
        }

        return keyList;
    }

    // 返回map所有的键值对
    public List<Map.Entry<K, V>> entries() {
        LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>();
        Node<K, V> p = head.next;
        while (p != tail) {
            entryList.addLast(p);
            p = p.next;
        }
        return entryList;
    }

    public void display() {
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            System.out.print(p.key + ":" + p.val + " -> ");
        }
        System.out.print("null");
        System.out.println();
    }


    public static void main(String[] args) {
        MyListMap<Integer, Integer> myListMap = new MyListMap<>();

        myListMap.put(1, 11);
        myListMap.put(2, 22);
        myListMap.put(3, 33);
        myListMap.put(4, 44);
//        List<Map.Entry<Integer,Integer>> allEntries = myListMap.entries();
//        for(Map.Entry<Integer,Integer> entry : allEntries){
//            System.out.print(entry.getKey() + ":" + entry.getValue() + " -> ");
//        }
//        System.out.print("null");
        myListMap.display();
        myListMap.put(2,55);
        myListMap.display();

        myListMap.remove(1);
        myListMap.display();
    }

}
