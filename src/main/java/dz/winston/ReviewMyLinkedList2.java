package dz.winston;

import java.util.NoSuchElementException;

/**
 * @Author: EpochDZ
 * @Date: 18:51 2022/8/4
 * @Description: 单链表
 * @Version v1.0
 */
public class ReviewMyLinkedList2 {
    // 节点
    private class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 元素个数
    private int size;
    // 虚拟头节点、尾节点
    private static Node head, tail;
    //初始化

    public ReviewMyLinkedList2() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        this.size = 0;
    }

    /****** 增 ******/
    // 在开头添加元素
    public void addFirst(int val) {
        Node x = new Node(val);
        Node temp = head.next;
        // head -> x -> temp
        head.next = x;
        x.next = temp;
        size++;
    }

    // 在结尾添加元素
    public void addLast(int val) {
        Node x = new Node(val);
        Node temp = getNode(size - 1);
        // temp -> x -> tail
        temp.next = x;
        x.next = tail;
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
        Node temp = getNode(index);
        //prev -> x -> temp;
        prev.next = x;
        x.next = temp;
        size++;
    }

    /****** 删 ******/
    // 删除开头元素（返回删除值）
    public int removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node first = head.next;
        // head -> first -> temp ...
        head.next = first.next;
        first.next = null;
        size--;
        return first.val;
    }

    // 删除结尾元素（返回删除值）
    public int removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node x = getNode(size - 1);
        Node temp;
        if (size - 2 >= 0) {
            temp = getNode(size - 2);
        } else {
            temp = head;
        }
        // temp -> x -> tail
        temp.next = tail;
        x.next = null;
        // temp -> tail
        size--;
        return x.val;
    }

    // 删除指定index中的元素（返回删除值）
    public int remove(int index) {
        checkElementIndex(index);
        if (index == 0) {
            removeFirst();
        }
        Node prev = getNode(index - 1);
        Node temp = getNode(index);
        // prev -> temp -> next
        prev.next = temp.next;
        temp.next = null;
        // prev -> next

        size--;
        return temp.val;
    }

    /****** 查 ******/
    // 返回开头元素值
    public int getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    // 返回结尾元素值
    public int getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(size - 1).val;
    }

    // 返回指定index中值
    public int get(int index) {
        checkElementIndex(index);
        Node p = getNode(index);
        return p.val;
    }

    /****** 改 ******/
    // 更改指定index中的值
    public void set(int index, int val) {
        checkElementIndex(index);
        Node p = getNode(index);

        int oldVal = p.val;
        p.val = val;
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
            System.out.print(p.val + " -> ");
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ReviewMyLinkedList2 reviewMyLinkedList2 = new ReviewMyLinkedList2();
        reviewMyLinkedList2.addFirst(1);
        reviewMyLinkedList2.addFirst(2);
        reviewMyLinkedList2.addLast(3);
        reviewMyLinkedList2.add(1,2);   //链表变为2-> 2 -> 1 -> 3
        reviewMyLinkedList2.display();
        System.out.println(reviewMyLinkedList2.get(1));            //返回2
        reviewMyLinkedList2.remove(2);  //现在链表是2 -> 2 ->3
        reviewMyLinkedList2.display();
        System.out.println(reviewMyLinkedList2.get(1));            //返回2
    }
}
