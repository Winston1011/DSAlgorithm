package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 11:06 2022/9/1
 * @Description: 递归实现单链表（性能较差，主要理解递归、铺垫回溯 动归）
 * @Version v1.0
 */
public class MyRecursiveList {
    // 单链表节点定义
    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 第一个节点
    private static Node first = null;
    // 当前元素大小
    private static int size = 0;

    // 构造函数
    public MyRecursiveList() {
    }

    /******* 增 *********/
    // 在头新增
    public void addFirst(int val) {
        Node x = new Node(val);
        x.next = first;
        first = x;
        size++;
    }

    // 在尾新增
    public void addLast(int val) {
        first = addLast(first, val);
        size++;
    }

    // a -> b -> c -> d -> null
    private Node addLast(Node node, int val) {
        // base case 当在d后新增e
        if (node == null) {
            return new Node(val);
        }
        // d.next = addLast(null, val)
        node.next = addLast(node.next, val);
        return node;
    }


    // 指定index新增
    public void add(int index, int val) {
        checkPositionIndex(index);
        if (index == size) {
            addLast(val);
            return;
        }
        first = add(first, index, val);
        size++;
    }

    // a -> b -> c -> null,在指定node之后index添加node
    private Node add(Node node, int index, int val) {
        // base case
        if (index == 0) {
            Node x = new Node(val);
            x.next = node;
            return x;
        }
        // node.next之后index-1 添加node,
        node.next = add(node.next, index - 1, val);
        return node;
    }


    /******* 删 *********/
    // 在头删除
    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        first = first.next;
    }

    // 在尾删除
    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        first = removeLast(first);
    }

    // a -> b -> c -> null
    private Node removeLast(Node node) {
        // base case
        if (node.next == null) {
            return null;
        }
        // b.next = removeLast(c)
        node.next = removeLast(node.next);
        return node;
    }

    // 指定index删除
    public void remove(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        checkElementIndex(index);
        first = remove(first, index);
    }

    // a -> b -> c -> d -> null,删除从node开始index之后的节点
    private Node remove(Node node, int index) {
        // base case
        if (index == 0) {
            return node.next;
        }
        // 删除从node.next 之后index-1之后的节点, b.next = remove(c,0)
        node.next = remove(node.next, index - 1);
        return node;
    }


    /******* 查 *********/
    // 查询第一个
    public Node getFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        return first;
    }

    // 查询最后一个
    public Node getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        // 递归获取最后一个
        return getLast(first);
    }

    // 从node开始获取直到 .next = null
    private Node getLast(Node node) {
        // base case
        if (node.next == null) {
            return node;
        }
        // 从node.next 开始获取
        return getLast(node.next);
    }

    // 查询指定index
    public Node get(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        checkElementIndex(index);
        //从first获取到index的node
        return getNode(index);
    }

    /******* 改 *********/
    // 指定位置更改val
    public void set(int index, int val) {
        if (isEmpty()) {
            throw new IllegalArgumentException("此链表为空");
        }
        checkElementIndex(index);
        Node x = getNode(index);
        x.val = val;
    }


    /******* 工具函数 *********/
    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取链表元素个数
    private int size() {
        return size;
    }

    // 删除元素：检查索引是否越界
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 新增元素：检查索引是否越界
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 获取指定index处的节点
    private Node getNode(int index) {
        /**
         * 1. for循环遍历
         */
//        Node p = first;
//        for (int i = 0; i < index; i++) {
//            p = p.next;
//        }
//        return p;
        /**
         * 2. 递归
         */
        return getNode(first, index);
    }

    // 递归获取指定index节点的函数：从node开始第index后的
    private Node getNode(Node node, int index) {
        // base case
        if (index == 0) {
            return node;
        }
        // 从node.next 开始第index-1 后的
        return getNode(node.next, index - 1);
    }

    // 打印
    public void display() {
        //
        display(first);
    }

    // 递归：a -> b -> c -> null
    private void display(Node node) {
        // base case
        if (node == null) {
            System.out.println("null");
            return;
        }
        System.out.print(node.val + " -> ");
        display(node.next);
    }


    public static void main(String[] args) {
        MyRecursiveList myRecursiveList = new MyRecursiveList();
        myRecursiveList.addLast(1);
        myRecursiveList.addLast(2);
        myRecursiveList.addLast(3);
        myRecursiveList.addLast(4);
        myRecursiveList.addLast(5);
        myRecursiveList.addLast(6);

        myRecursiveList.display();

        myRecursiveList.remove(2);
        myRecursiveList.display();


    }

}
