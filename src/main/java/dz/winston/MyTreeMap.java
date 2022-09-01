package dz.winston;


import java.util.NoSuchElementException;

/**
 * @Author: EpochDZ
 * @Date: 16:04 2022/9/1
 * @Description: 自设计treeMap（BST） 二叉搜索树
 * 功能如下：
 * 1、增：新增key
 * 2、删：删除指定key的val、删除最大key对应treeNode、删除最小key对应treeNode
 * 3、改：改key对应的val
 * 4、查：包含查询指定key的val、是否存在key、返回最大的key、返回最小的key、返回某个key的rank值、根据索引值（即rank值）查询返回某个key、返回小于等于key的最大键、返回大于等于key的最小键
 * 5、工具函数： 是否为空、某个节点为根的所有子节点个数、
 * @Version v1.0
 */
public class MyTreeMap<K extends Comparable<K>, V> {
    // 树节点
    private class TreeNode {
        K key;
        V val;
        TreeNode left;
        TreeNode right;
        // 记录以该节点为根的 BST 有多少个节点
        int size;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.left = this.right = null;
            this.size = 1;
        }
    }

    // root根节点
    private TreeNode root = null;

    // 构造函数
    public MyTreeMap() {
    }

    /**
     * 增和改
     */
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        V oldVal = get(key);
        root = put(root, key, val);
        return oldVal;
    }

    private TreeNode put(TreeNode node, K key, V val) {
        // base case，当左或者右子树为null，则返回
        if (node == null) {
            return new TreeNode(key, val);
        }
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            // node.key > key, 往左子树中放
            node.left = put(node.left, key, val);
        } else if (cmp < 0) {
            // node.key < key, 往右子树中放
            node.right = put(node.right, key, val);
        } else {
            // node.key == key的时候,更改val
            node.val = val;
        }
        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    /**
     * 查
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        TreeNode x = get(root, key);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    //查询node为根节点的树中键为key的TreeNode
    private TreeNode get(TreeNode node, K key) {
        // base case 左右子树都递归结束也没有找到
        if (node == null) {
            return null;
        }
        // 比较node.key 和key的大小
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            // node.key > key 即需要在node的左子树中继续查找
            return get(node.left, key);
        } else if (cmp < 0) {
            // node.key < key 即需要在node的右子树中继续查找
            return get(node.right, key);
        }
        // node.key == key，找到直接返回
        return node;
    }

    // 获取最小key的val
    public V getMin() {
        if (isEmpty()) {
            System.out.println("空树");
            return null;
        }
        TreeNode x = getMin(root);
        return x.val != null ? x.val : null;
    }

    // 递归遍历获取最小key的node
    private TreeNode getMin(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    // 获取最大key的val
    public V getMax() {
        if (isEmpty()) {
            System.out.println("空树");
            return null;
        }
        TreeNode x = getMax(root);
        return x.val != null ? x.val : null;
    }

    // 递归遍历获取最小key的node
    private TreeNode getMax(TreeNode node) {
        if (node.right == null) {
            return node;
        }
        return getMin(node.right);
    }

    // 返回小于等于 key 的最大的键
    public K floorKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("空树");
        }
        TreeNode x = floorKey(root, key);
        return x.key;
    }

    private TreeNode floorKey(TreeNode node, K key) {
        if (node == null) {
            // key 不存在
            return null;
        }
        int cmp = node.key.compareTo(key);
        // node.key > key，去左子树找
        if (cmp > 0) {
            return floorKey(node.left, key);
        }
        // node.key < key，去右子树找
        if (cmp < 0) {
            TreeNode x = floorKey(node.right, key);
            if (x == null) {
                return node;
            }
            return x;
        }
        // node.key == key
        return node;
    }

    // 返回大于等于 key 的最小的键
    public K ceilingKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode x = ceilingKey(root, key);
        return x.key;
    }

    private TreeNode ceilingKey(TreeNode node, K key) {
        if (node == null) {
            // key 不存在
            return null;
        }
        int cmp = node.key.compareTo(key);
        // node.key > key，去左子树找
        if (cmp > 0) {
            TreeNode x = ceilingKey(node.left, key);
            if (x == null) {
                return node;
            }
            return x;
        }
        // node.key < key，去右子树找
        if (cmp < 0) {
            return ceilingKey(node.right, key);
        }
        // node.key == key
        return node;
    }

    // 查看是否存在
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    // 返回小于 key 的键的个数
    public int rank(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rank(root, key);
    }

    // 返回以 node 为根的 BST 中小于 key 的键的个数
    private int rank(TreeNode node, K key) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // key < node.key
            // 和 node 以及 node.right 没啥关系了
            // 因为它们太大了
            return rank(node.left, key);
        } else if (cmp > 0) {
            // key > node.key
            // node 和 node.left 左子树都是比 key 小的
            return size(node.left) + 1 + rank(node.right, key);
        } else {
            // key == node.key
            return size(node.left);
        }
    }

    // 返回索引为 i 的键，i 从 0 开始计算
    public K select(int i) {
        if (i < 0 || i >= size()) {
            throw new IllegalArgumentException();
        }

        TreeNode x = select(root, i);
        return x.key;
    }

    // 返回以 node 为根的 BST 中索引为 i 的那个节点
    private TreeNode select(TreeNode node, int i) {
        int n = size(node.left);

        if (n > i) {
            // n == 10, i == 3
            return select(node.left, i);
        } else if (n < i) {
            // n == 3, i == 10
            return select(node.right, i - n - 1);
        } else {
            // i == n
            // node 就是索引为 i 的那个节点
            return node;
        }
    }

    /**
     * 删
     */
    // 删除指定key的node,并返回val
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        V deletedVal = get(key);
        root = remove(root, key);
        return deletedVal;
    }

    // 递归删除
    private TreeNode remove(TreeNode node, K key) {
        // base case（没有找到key）
        if (node == null) {
            System.out.println("没有此key");
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            //node.key > key,往左子树找并递归删
            node.left = remove(node.left, key);
        } else if (cmp < 0) {
            //node.key < key,往右子树找并递归删
            node.right = remove(node.right, key);
        } else {
            //node.key == key，找到即删除此key

            // 1、此node没有左右子树
            // 2、此node含有一个子树（左或右边）
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // 3、此node含有左右子树
            // 找到右子树中最小的节点
            TreeNode rightMin = getMin(node.right);
            // 删除右子树中最小节点
            node.right = removeMin(node.right);
            // 替换
            rightMin.left = node.left;
            rightMin.right = node.right;
            node = rightMin;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void removeMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("空树");
        }
        root = removeMin(root);
    }

    // 删除并返回以 node 为根的 BST 中最小的那个节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    /**
     * 工具函数
     */
    public int size() {
        return size(root);
    }

    // 返回以 node 节点为根的 BST 有多少节点
    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // 中序遍历打印
    public void midTraversePrint() {
        midTraverse(root);
    }

    // 中序递归遍历
    private void midTraverse(TreeNode node) {
        // base case
        if (node == null) {
            return;
        }
        midTraverse(node.left);
        System.out.print(node.val);
        midTraverse(node.right);
    }


    public static void main(String[] args) {
        MyTreeMap<Integer, String> myTreeMap = new MyTreeMap<>();

        myTreeMap.put(8, "U");
        myTreeMap.put(2, " ");
        myTreeMap.put(6, "e");
        myTreeMap.put(1, "I");
        myTreeMap.put(4, "o");
        myTreeMap.put(5, "v");
        myTreeMap.put(7, " ");
        myTreeMap.put(3, "L");
        System.out.println(myTreeMap.size());

        System.out.println("-------------- 中序遍历打印输出--------------");
        myTreeMap.midTraversePrint();
        System.out.println();

        System.out.println(myTreeMap.getMin());
        System.out.println(myTreeMap.getMax());

        String str = myTreeMap.remove(1);
        System.out.println("-------------- 中序遍历打印输出 --------------");
        myTreeMap.midTraversePrint();
        System.out.println();

        myTreeMap.put(1, "I");
        System.out.println("-------------- 中序遍历打印输出 --------------");
        myTreeMap.midTraversePrint();
        System.out.println();

        System.out.println("-------------- floorKey --------------");
        System.out.println(myTreeMap.floorKey(9));

        System.out.println("-------------- ceilKey --------------");
        System.out.println(myTreeMap.ceilingKey(0));

        System.out.println("-------------- rank --------------");
        System.out.println(myTreeMap.rank(5));

        System.out.println("-------------- select --------------");
        System.out.println(myTreeMap.select(7));
    }

}
