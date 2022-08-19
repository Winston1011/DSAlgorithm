package dz.winston;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: EpochDZ
 * @Date: 16:38 2022/8/19
 * @Description:
 * @Version v1.0
 */
public class ReviewMyHashMap {
    // map键值对连着的链表(hashmap的槽）
    static class MySlot {
        //map型单链表节点
        public static class Node implements Map.Entry<Integer, Integer> {
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

        // 头尾节点
        private final Node head;
        private final Node tail;
        //当前元素大小
        private int size;

        // 构造函数
        public MySlot() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            this.size = 0;
        }

        //增或改（新增键值对key-val或改key对应val为新值）
        public void put(int key, int val) {
            //查找是否存在key
            Node p = getNode(key);
            if (p != null) {
                // 更新
                p.val = val;
                return;
            }
            // 从头新增
            Node x = new Node(key, val);
            Node temp = head.next;
            x.next = temp;
            head.next = x;
            size++;
        }

        //删（删除某个key对应的node）
        public int remove(int key) {
            Node p = getNode(key);
            if (p == null) {
                throw new IllegalArgumentException("不存在此key");
            }
            int oldValue = p.val;
            Node prev = head;
            while (prev.next != p) {
                prev = prev.next;
            }
            prev.next = p.next;
            size--;
            return oldValue;
        }

        //查(根据key查询val）
        public int get(int key) {
            Node p = getNode(key);
            if (p != null) {
                return p.val;
            }
            return -1;
        }

        //是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        // 根据key获取节点
        private Node getNode(int key) {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
                if (p.key == key) {
                    return p;
                }
            }
            return null;
        }

        public boolean containsKey(int key) {
            Node temp = getNode(key);
            return temp != null;
        }

        public int size() {
            return size;
        }

        public void display() {
            for (Node p = head.next; p != tail; p = p.next) {
                System.out.print(p.key + ":" + p.val + " -> ");
            }
            System.out.print("null");
            System.out.println();
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

        // 返回所有键值对（linkedlist存储）
        public LinkedList<Map.Entry<Integer, Integer>> entries() {
            LinkedList<Map.Entry<Integer, Integer>> entryList = new LinkedList<>();
            Node p = head.next;
            while (p.next != tail) {
                entryList.addLast(p);
                p = p.next;
            }
            return entryList;
        }
    }

    // 槽类型的数组
    private MySlot[] table;
    // 当前元素大小（存储键值对个数）
    private int size;
    // 默认数组大小
    private final static int DEFAULT_CAPACITY = 16;
    // 负载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public ReviewMyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    // 构造函数
    public ReviewMyHashMap(int cap) {
        table = new MySlot[cap];
        for (int i = 0; i < table.length; i++) {
            table[i] = new MySlot();
        }
        this.size = 0;
    }

    // 新增或更改
    public void put(int key, int val) {
        if (key < 0) {
            throw new IllegalArgumentException("key不可为负数");
        }
        // 扩容
        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            resize(table.length * 2);
        }
        MySlot mySlot = table[hash(key)];
        // 如果 key 之前不存在，则插入，size 增加
        if (!mySlot.containsKey(key)) {
            size++;
        }
        mySlot.put(key, val);
    }

    // 删
    public int remove(int key) {
        MySlot mySlot = table[hash(key)];
        // 如果 key 之前存在，则删除，size 减少
        if (!mySlot.containsKey(key)) {
            throw new IllegalArgumentException("此table不含有此key");
        }
        size--;
        return mySlot.remove(key);
    }

    // 查
    public int get(int key) {
        if (key < 0) {
            throw new IllegalArgumentException("key不可为负数");
        }
        MySlot mySlot = table[hash(key)];
        return mySlot.get(key);
    }

    public boolean containsKey(int key) {
        MySlot mySlot = table[hash(key)];
        return mySlot.containsKey(key);
    }

    // 哈希函数
    private int hash(int key) {
        // 保证非负数
        return (key & 0x7fffffff) % (table.length);
    }

    // 是否为空
    private boolean isEmpty() {
        return size == 0;
    }

    // 扩容或缩容
    private void resize(int newCap) {
        // 构造一个newCap的hashmap
        ReviewMyHashMap newMap = new ReviewMyHashMap(newCap);
        // 取出原table中所有键值对，插入新hashmap
        LinkedList<Map.Entry<Integer, Integer>> allEntries = new LinkedList<>();
        for (MySlot mySlot : table) {
            for (Map.Entry<Integer, Integer> entry : mySlot.entries()) {
                int key = entry.getKey();
                int val = entry.getValue();
                newMap.put(key, val);
            }
        }
        // 将原table替换为新hashmap的table
        this.table = newMap.table;
    }

    public static void main(String[] args) {
        ReviewMyHashMap reviewMyHashMap = new ReviewMyHashMap();
        reviewMyHashMap.put(1, 11);
        reviewMyHashMap.put(2, 22);
        reviewMyHashMap.put(3, 33);
        reviewMyHashMap.put(3, 44);
        reviewMyHashMap.put(17,12);

        int v1 = reviewMyHashMap.get(1);

        int v2 = reviewMyHashMap.remove(2);
        boolean ret1 = reviewMyHashMap.containsKey(2);

        boolean ret2 = reviewMyHashMap.containsKey(3);

        MySlot[] slots = reviewMyHashMap.table;
        LinkedList<Integer> allKeysList = new LinkedList<>();
        for (MySlot slot : slots) {
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
