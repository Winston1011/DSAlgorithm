package dz.winston;

import java.util.Arrays;

/**
 * @Author: EpochDZ
 * @Date: 18:49 2022/8/8
 * @Description: 图的并查集-快速合并顶点（值存放父节点）
 * @Version v1.0
 */
public class MyQuickUnion {
    private final int[] root;

    public MyQuickUnion(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        while (x != root[x]) {
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) throws Exception {
        MyQuickUnion myQuickUnion = new MyQuickUnion(10);
        // 1-2-5-6-7 3-8-9 4
        myQuickUnion.union(1, 2);
        myQuickUnion.union(2, 5);
        myQuickUnion.union(5, 6);
        myQuickUnion.union(6, 7);
        myQuickUnion.union(3, 8);
        myQuickUnion.union(8, 9);
        System.out.println(myQuickUnion.connected(1, 5)); // true
        System.out.println(myQuickUnion.connected(5, 7)); // true
        System.out.println(myQuickUnion.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        myQuickUnion.union(9, 4);
        System.out.println(myQuickUnion.connected(4, 9)); // true
        System.out.println(Arrays.toString(myQuickUnion.root));

    }
}

