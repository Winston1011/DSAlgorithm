package leetcode.practice;

/**
 * @Author: EpochDZ
 * @Date: 10:55 2022/8/9
 * @Description: 省份数量 —— 图的并查集解决
 * @Version v1.0
 */
public class LC547ProvinceNum {

    public int findProvinceNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }

    public static class UnionFind {
        // 顶点信息（对应城市信息）
        private final int[] root;
        // 记录每个顶点当前所在高度（路径优化）
        private final int[] rank;
        // 不同根节点数量（省份数量）
        private int count;

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        // 查找根节点（递归）
        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        //基于路径优化合并（同时记录省份数量变化）
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
                count--;
            }
        }

        //返回省份数量
        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        LC547ProvinceNum lc547ProvinceNum = new LC547ProvinceNum();
        int[][] isConnected = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        int provinceNum = lc547ProvinceNum.findProvinceNum(isConnected);
        System.out.print("省份数量为： ");
        System.out.println(provinceNum);
    }
}
