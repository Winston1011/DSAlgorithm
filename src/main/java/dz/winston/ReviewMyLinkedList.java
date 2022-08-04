package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 16:35 2022/8/4
 * @Description:
 * @Version v1.0
 */
public class ReviewMyLinkedList {
    //链表节点
    public class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    //定义虚拟头节点、尾节点
    private static Node head, tail;
    //定义当前元素个数
    private int size;

    //初始化
    public ReviewMyLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    /****** 增 ******/
    // 在开头添加元素
    public void addFirst(int val) {
        Node x = new Node(val);
        Node temp = head.next;
        // head <-> x <-> temp
        head.next = x;
        x.prev = head;
        x.next = temp;
        temp.prev = x;
        size++;
    }

    // 在结尾添加元素
    public void addLast(int val) {
        Node x = new Node(val);
        Node temp = tail.prev;
        // temp <-> x <-> tail
        temp.next = x;
        x.prev = temp;
        x.next = tail;
        tail.prev = x;
        size++;
    }

    // 在指定index添加元素
    public void add(int index, int val) {
        checkPositionIndex(index);
        Node x = new Node(val);
        Node prev;
        if (index - 1 >= 0) {
            prev = getNode(index - 1);
        } else {
            prev = head;
        }
        Node next = getNode(index);
        //prev <-> x <-> next
        prev.next = x;
        x.prev = prev;
        x.next = next;
        next.prev = x;
        size++;
    }

    /****** 删 ******/
    // 删除开头元素（返回删除值）
    public int removeFirst() {
        Node x = head.next;
        head.next = x.next;
        x.next.prev = head;
        x.next = null;
        x.prev = null;
        int oldValue = x.val;
        size--;
        return oldValue;
    }

    // 删除结尾元素（返回删除值）
    public int removeLast() {
        Node x = tail.prev;
        x.prev.next = tail;
        tail.prev = x.prev;
        x.next = null;
        x.prev = null;
        int oldValue = x.val;
        size--;
        return oldValue;
    }

    // 删除指定index中的元素（返回删除值）
    public int remove(int index) {
        checkElementIndex(index);
        Node prev;
        if (index - 1 >= 0) {
            prev = getNode(index - 1);
        } else {
            prev = head;
        }
        Node x = getNode(index);
        prev.next = x.next;
        x.next.prev = prev;
        x.next = null;
        x.prev = null;
        int oldValue = x.val;
        size--;
        return oldValue;
    }

    /****** 查 ******/
    // 返回开头元素值
    public int getFirst() {
        Node x = head.next;
        return x.val;
    }

    // 返回结尾元素值
    public int getLast() {
        Node x = tail.prev;
        return x.val;
    }

    // 返回指定index中值
    public int get(int index) {
        checkElementIndex(index);
        Node x = getNode(index);
        return x.val;
    }

    /****** 改 ******/
    // 更改指定index中的值
    public void set(int index, int val) {
        checkElementIndex(index);
        Node x = new Node(val);
        Node prev;
        if (index - 1 >= 0) {
            prev = getNode(index - 1);
        } else {
            prev = head;
        }
        Node next = getNode(index);
        // prev <-> x
        prev.next = x;
        x.prev = prev;
        x.next = next.next;
        next.next.prev = x;
        next.next = null;
        next.prev = null;
    }

    /****** 其他 ******/
    // 判断是否为空
    private boolean isEmpty() {
        return this.size == 0;
    }

    // 返回当前元素个数大小
    private int size() {
        return this.size;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // 获取指定index的Node
    private Node getNode(int index) {
        Node p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " <-> ");
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ReviewMyLinkedList reviewMyLinkedList = new ReviewMyLinkedList();

        reviewMyLinkedList.addLast(1);
        reviewMyLinkedList.addLast(2);
        reviewMyLinkedList.addLast(3);
        reviewMyLinkedList.addLast(4);
        reviewMyLinkedList.addLast(5);
        reviewMyLinkedList.addFirst(10);
        reviewMyLinkedList.display();

        reviewMyLinkedList.add(1,6);
        reviewMyLinkedList.display();

        reviewMyLinkedList.removeFirst();
        reviewMyLinkedList.display();

        reviewMyLinkedList.removeLast();
        reviewMyLinkedList.display();

        reviewMyLinkedList.remove(2);
        reviewMyLinkedList.display();

        reviewMyLinkedList.set(2,2);
        reviewMyLinkedList.display();



    }

}
