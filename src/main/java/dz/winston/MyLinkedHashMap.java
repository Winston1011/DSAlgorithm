package dz.winston;

import java.util.HashMap;

/**
 * @Author: EpochDZ
 * @Date: 20:44 2022/8/10
 * @Description: hashmap+linkedList 可顺序读取键值对
 * @Version v1.0
 */
public class MyLinkedHashMap {

    // <K,Node<K,V>>的hashmap
    private final HashMap<Integer, Node> map = new HashMap<>();

    // 链表中头节点、尾节点
    private final Node head, tail;

    //构造函数
    public MyLinkedHashMap() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    //双链表节点
    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 新Node新增到链表尾部
    private void addNodeLast(Node node) {
        Node temp = tail.prev;
        temp.next = node;
        node.prev = temp;

        node.next = tail;
        tail.prev = node;
    }

    // 删除链表中的Node
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        // prev <-> x <-> next

        prev.next = next;
        next.prev = prev;

        node.next = node.prev = null;
    }


    // 增或改
    public int put(int key, int val) {
        if (key == -1) {
            throw new IllegalArgumentException("key is -1");
        }
        if (!map.containsKey(key)) {
            Node node = new Node(key, val);
            map.put(key, node);
            addNodeLast(node);
            return node.val;
        }
        Node temp = map.get(key);
        int oldValue = temp.val;
        temp.val = val;
        return oldValue;
    }

    // 查
    public int get(int key) {
        if (key == -1) {
            throw new IllegalArgumentException("key is -1");
        }
        Node entry = map.get(key);
        if (entry != null) {
            return entry.val;
        }
        return -1;
    }

    public boolean containsKey(int key) {
        if (key == -1) {
            throw new IllegalArgumentException("key is -1");
        }
        return map.containsKey(key);
    }

    // 删
    public int remove(int key) {
        if (key == -1) {
            throw new IllegalArgumentException("key is -1");
        }
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        int oldValue = node.val;
        removeNode(node);
        map.remove(key);
        return oldValue;
    }

    public static void main(String[] args) {
        MyLinkedHashMap myLinkedHashMap = new MyLinkedHashMap();
        int ret1 = myLinkedHashMap.put(1, 2);
        int ret2 = myLinkedHashMap.put(2, 4);
        int ret3 = myLinkedHashMap.put(3, 6);
        int ret4 = myLinkedHashMap.put(4, 8);
        int ret5 = myLinkedHashMap.put(5, 10);

        int ret6 = myLinkedHashMap.get(5);
        boolean ret7 = myLinkedHashMap.containsKey(2);

        int ret8 = myLinkedHashMap.remove(3);
        int ret9 = myLinkedHashMap.remove(1);
    }


}
