package dz.winston;

import java.util.Arrays;

/**
 * @Author: EpochDZ
 * @Date: 21:29 2022/8/8
 * @Description: 基于路径压缩的按秩合并优化的「并查集」（find-递归，union-按秩序合并）
 * @Version v1.0
 */
public class MyUnionFindByOrderAndRecursive {
    // 存放顶点信息
    private final int[] root;
    // 记录每个顶点当前所在高度
    private final int[] rank;

    public MyUnionFindByOrderAndRecursive(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            // 初始化为1
            rank[i] = 1;
        }
    }

    // 递归查找
    public int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    // 按秩序优化的合并
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }


    public static void main(String[] args) throws Exception {
        MyUnionFindByOrderAndRecursive myUnionFindByOrderAndRecursive = new MyUnionFindByOrderAndRecursive(10);
        // 1-2-5-6-7 3-8-9 4
        myUnionFindByOrderAndRecursive.union(1, 2);
        myUnionFindByOrderAndRecursive.union(2, 5);
        myUnionFindByOrderAndRecursive.union(5, 6);
        myUnionFindByOrderAndRecursive.union(6, 7);
        myUnionFindByOrderAndRecursive.union(3, 8);
        myUnionFindByOrderAndRecursive.union(8, 9);
        System.out.println(myUnionFindByOrderAndRecursive.connected(1, 5)); // true
        System.out.println(myUnionFindByOrderAndRecursive.connected(5, 7)); // true
        System.out.println(myUnionFindByOrderAndRecursive.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        myUnionFindByOrderAndRecursive.union(9, 4);
        System.out.println(myUnionFindByOrderAndRecursive.connected(4, 9)); // true
        System.out.println(Arrays.toString(myUnionFindByOrderAndRecursive.root));
        System.out.println(Arrays.toString(myUnionFindByOrderAndRecursive.rank));

    }
}

