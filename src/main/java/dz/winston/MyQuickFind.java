package dz.winston;

import java.util.Arrays;

/**
 * @Author: EpochDZ
 * @Date: 18:29 2022/8/8
 * @Description: 图的并查集-快速查找（值存放根节点）
 * @Version v1.0
 */
public class MyQuickFind {
    //定义存放顶点信息的数组
    private final int[] root;

    //构造函数
    public MyQuickFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    //查找某顶点的根节点
    public int find(int x) {
        return root[x];
    }

    //合并两顶点
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            for (int i = 0; i < root.length; i++) {
                if (root[i] == rootY) {
                    root[i] = rootX;
                }
            }
        }
    }

    // 判断是否连通
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        MyQuickFind myUnionFind = new MyQuickFind(10);
        // 1-2-5-6-7 3-8-9 4
        myUnionFind.union(1, 2);
        myUnionFind.union(2, 5);
        myUnionFind.union(5, 6);
        myUnionFind.union(6, 7);
        myUnionFind.union(3, 8);
        myUnionFind.union(8, 9);
        System.out.println(myUnionFind.connected(1, 5)); // true
        System.out.println(myUnionFind.connected(5, 7)); // true
        System.out.println(myUnionFind.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        myUnionFind.union(9, 4);
        System.out.println(myUnionFind.connected(4, 9)); // true

        System.out.println(Arrays.toString(myUnionFind.root));
    }


}
