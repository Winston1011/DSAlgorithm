package dz.winston;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: EpochDZ
 * @Date: 17:10 2022/8/12
 * @Description: 可等概率随机弹出某个键值对
 * @Version v1.0
 */
public class MyArrayHashMap {
    // 存放K和数组Index的hashmap
    private static final HashMap<Integer, Integer> hashMap = new HashMap<>();
    // 存放键值对的arrayList
    private static final MyArrayList<Node<Integer, Integer>> arrayList = new MyArrayList<>();

    // 键值对节点
    public static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return val;
        }

        @Override
        public V setValue(V value) {
            this.val = value;
            return value;
        }
    }

    // 随机数对象
    private final Random r = new Random();

    // 构造函数
    public MyArrayHashMap() {

    }

    // 增或更改(更改则返回就val
    public int put(int key, int val) {
        Node<Integer, Integer> x = new Node<>(key, val);
        // 1、已经存在此键，更新
        if (containsKey(key)) {
            // 先获取arrayList的index
            int index = hashMap.get(key);
            // 查找arrayList中的node并更新val
            Node<Integer, Integer> oldNode = arrayList.get(index);
            int oldValue = oldNode.val;
            oldNode.setValue(val);
            return oldValue;
        }
        // 2、不存在，直接新增
        // 添加到arrayList中尾部
        arrayList.addLast(x);
        // 添加键值对到map
        hashMap.put(key, arrayList.size() - 1);
        return val;
    }

    // 删
    public int remove(int key) {
        // 查询是否存在
        if (!containsKey(key)) {
            System.out.println("此key不存在，无法删除");
            return -1;
        }
        // 查出所在arrayList所在index
        int index = hashMap.get(key);
        // 待删除node
        Node<Integer, Integer> node1 = arrayList.get(index);
        // 尾部node
        Node<Integer, Integer> node2 = arrayList.get(arrayList.size() - 1);

        // arrayList交换操作
        arrayList.set(index, node2);
        arrayList.set(arrayList.size() - 1, node1);

        // map键值对交换
        hashMap.put(key, arrayList.size() - 1);
        hashMap.put(node2.key, index);

        // 删除最后一个交换后的数组元素
        arrayList.removeLast();
        // 删除map的待删除的键值对
        hashMap.remove(key);
        return node1.val;
    }

    // 查
    public int get(int key) {
        if (!containsKey(key)) {
            System.out.println("无此key");
            return -1;
        }
        int index = hashMap.get(key);
        Node<Integer, Integer> node = arrayList.get(index);
        return node.val;
    }

    public boolean containsKey(int key) {
        return hashMap.containsKey(key);
    }

    // 随机弹出一个键
    public int pop() {
        int n = arrayList.size();
        int randomIndex = r.nextInt(n);
        return arrayList.get(randomIndex).key;
    }

    public int size() {
        return hashMap.size();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    public static void main(String[] args) {
        MyArrayHashMap map = new MyArrayHashMap();
        map.put(2, 1);
        map.put(2, 2);
        map.put(3,4);
        map.put(4,22);
        map.put(22,11);

        System.out.println(map.get(2));
        System.out.println(map.pop());
    }

}
