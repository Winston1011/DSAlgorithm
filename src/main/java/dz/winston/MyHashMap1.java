package dz.winston;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: EpochDZ
 * @Date: 14:36 2022/8/10
 * @Description:
 * @Version v1.0
 */
public class MyHashMap1 {
    static class Slot {
        // 单链表map节点
        private static class Node implements Map.Entry<Integer, Integer> {
            int key;
            int val;
            Node next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            @Override
            public Integer getKey() {
                return this.key;
            }

            @Override
            public Integer getValue() {
                return this.val;
            }

            @Override
            public Integer setValue(Integer value) {
                int oldVal = this.val;
                this.val = value;
                return oldVal;
            }
        }

        // 虚拟头节点、尾节点
        private final Node head, tail;
        // 当前元素大小
        private int size;

        // 构造函数
        public Slot() {
            //  初始化头、尾节点
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            // 将头结点和尾结点相连
            head.next = tail;
            this.size = 0;
        }

        // 增或改
        public int put(int key, int val) {
            if (key == -1) {
                throw new IllegalArgumentException("Key is -1");
            }
            Node temp = getNode(key);
            // 改值
            if (temp != null) {
                return temp.setValue(val);
            }
            // 在头新增
            Node x = new Node(key, val);
            Node temp2 = head.next;
            head.next = x;
            x.next = temp2;
            size++;
            return x.val;
        }

        // 删
        public int remove(int key) {
            if (key == -1) {
                throw new IllegalArgumentException("Key is -1");
            }
            Node prev = head;
            for (Node p = head.next; p != tail; p = p.next) {
                if (p.key == key) {
                    prev.next = p.next;
                    size--;
                    return p.val;
                }
                prev = p;
            }
            return -1;
        }

        // 查
        public int get(int key) {
            if (key == -1) {
                throw new IllegalArgumentException("Key is -1");
            }
            Node ret = getNode(key);
            if (ret != null) {
                return ret.val;
            }
            return -1;
        }

        public boolean containsKey(int key) {
            if (key == -1) {
                throw new IllegalArgumentException("Key is -1");
            }
            Node temp = getNode(key);
            return temp != null;
        }

        // 根据key获取Node
        private Node getNode(int key) {
            for (Node p = head.next; p != tail; p = p.next) {
                if (p.key == key) {
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
        public List<Integer> keys() {
            LinkedList<Integer> keyList = new LinkedList<>();

            Node p = head.next;
            while (p != tail) {
                keyList.addLast(p.key);
                p = p.next;
            }

            return keyList;
        }

        // 返回map所有的键值对
        public List<Map.Entry<Integer, Integer>> entries() {
            LinkedList<Map.Entry<Integer, Integer>> entryList = new LinkedList<>();
            Node p = head.next;
            while (p != tail) {
                entryList.addLast(p);
                p = p.next;
            }
            return entryList;
        }

    }

    // 底层存储链表的数组table
    private Slot[] table;
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
        table = new Slot[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new Slot();
        }
        this.size = 0;
    }

    // 增或改
    public int put(int key, int val) {
        if (key == -1) {
            throw new IllegalArgumentException("Key is null");
        }
        // 扩容
        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            reSize(table.length * 2);
        }
        Slot slot = table[hash(key)];
        // 如果 key 之前不存在，则插入，size 增加
        if (!slot.containsKey(key)) {
            size++;
        }
        return slot.put(key, val);
    }

    // 查
    public int get(int key) {
        if (key == -1) {
            throw new IllegalArgumentException("Key is null");
        }
        Slot slot = table[hash(key)];
        return slot.get(key);
    }

    public boolean containsKey(int key) {
        if (key == -1) {
            throw new IllegalArgumentException("Key is null");
        }
        Slot slot = table[hash(key)];
        return slot.containsKey(key);
    }

    // 删
    public int remove(int key) {
        if (key == -1) {
            throw new IllegalArgumentException("Key is null");
        }
        Slot slot = table[hash(key)];
        // 如果 key 之前存在，则删除，size 减少
        if (slot.containsKey(key)) {
            size--;
            return slot.remove(key);
        }
        return -1;
    }

    // 其他函数
    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private int hash(int key) {
        // 保证非负数
        return (key & 0x7fffffff) % (table.length);
    }

    private void reSize(int newCap) {
        // 构造一个更大容量的 HashMap
        MyHashMap1 newMap = new MyHashMap1(newCap);
        // 穷举当前 HashMap 中的所有键值对
        for (Slot slot : table) {
            for (Map.Entry<Integer, Integer> entry : slot.entries()) {
                int key = entry.getKey();
                int val = entry.getValue();
                // 将键值对转移到新的 HashMap 中
                newMap.put(key, val);
            }
        }
        // 将当前 HashMap 的底层 table 换掉
        this.table = newMap.table;
    }

    public static void main(String[] args) {
        MyHashMap1 myHashMap1 = new MyHashMap1();
        myHashMap1.put(1, 11);
        myHashMap1.put(2, 22);
        myHashMap1.put(3, 33);
        myHashMap1.put(3, 44);

        int v1 = myHashMap1.get(1);

        int v2 = myHashMap1.remove(2);
        boolean ret1 = myHashMap1.containsKey(2);

        boolean ret2 = myHashMap1.containsKey(3);

        Slot[] slots = myHashMap1.table;
        LinkedList<Integer> allKeysList = new LinkedList<>();
        for (Slot slot : slots) {
            List<Integer> keysList = slot.keys();
            if (keysList.isEmpty()) {
                continue;
            }
            for (Integer key : keysList) {
                allKeysList.addLast(key);
            }
        }

        System.out.println(Arrays.toString(allKeysList.toArray()));
    }

}
