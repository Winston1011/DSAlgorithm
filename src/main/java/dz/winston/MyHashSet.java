package dz.winston;

import java.util.Map;

/**
 * @Author: EpochDZ
 * @Date: 16:16 2022/8/10
 * @Description:
 * @Version v1.0
 */
public class MyHashSet<K> {
    // val 占位符
    private static final Object PRESENT = new Object();
    // 底层 用线性探测法的MyHashMap
    private final MyHashMap2<K, Object> map = new MyHashMap2<>();


    public boolean add(K k) {
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        return map.remove(k) == PRESENT;
    }

    public boolean contains(K k) {
        return map.containsKey(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }


    public static void main(String[] args) {
        MyHashSet<Integer> myHashSet = new MyHashSet<>();

        boolean ret1 = myHashSet.add(1);
        boolean ret2 = myHashSet.add(44);
        boolean ret3 = myHashSet.add(22);
        boolean ret4 = myHashSet.add(44);

        boolean ret5 = myHashSet.contains(1);
        boolean ret6 = myHashSet.remove(22);
    }
}
