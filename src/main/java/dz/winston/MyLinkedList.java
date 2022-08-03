package dz.winston;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @Author: EpochDZ
 * @Date: 18:05 2022/8/2
 * @Description: 双链表
 * @Version v1.0
 */
public class MyLinkedList<E> {
    // 双链表节点
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    // 当前元素个数
    private int size;
    // 双链表头节点、尾节点
    private final Node<E> head, tail;

    // 构造函数初始化头尾节点
    public MyLinkedList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    /***** 增 *****/
    // 在数组最前面添加元素
    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        // head <-> temp
        temp.prev = x;
        x.next = temp;

        head.next = x;
        x.prev = head;
        // head <-> x <-> temp
        size++;
    }
    // 在最后添加元素
    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = tail.prev;
        // temp <-> tail
        temp.next = x;
        x.prev = temp;

        x.next = tail;
        tail.prev = x;
        // temp <-> x <-> tail
        size++;
    }
    // 在指定index添加element
    public void add(int index, E e) {
        checkPositionIndex(index);
        // 最后一个元素后插入
        if (index == size) {
            addLast(e);
            return;
        }
        // 第一个元素前插入
        if (index == 0) {
            addFirst(e);
            return;
        }
        Node<E> x = new Node<E>(e);
        Node<E> temp = getNode(index);
        Node<E> tempPrev = temp.prev;

        // tempPrev <-> x <-> temp
        x.prev = tempPrev;
        x.next = temp;

        tempPrev.next = x;
        temp.prev = x;

        size++;
    }

    /***** 删 *****/
    // 删除最后一个元素(返回删除的元素)
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> x = tail.prev;
        Node<E> temp = x.prev;

        // temp <-> x <-> tail
        tail.prev = temp;
        temp.next = tail;

        x.prev = x.next = null;

        size--;
        return x.element;
    }

    // 删除指定index的元素(返回删除的元素)
    public E remove(int index) {
        checkElementIndex(index);
        // 删除最后一个元素
        if (index == size) {
            removeLast();
        }
        // 删除第一个元素
        if (index == 0) {
            removeFirst();
        }

        Node<E> x = getNode(index);
        Node<E> xPrev = x.prev;
        Node<E> xNext = x.next;
        // xPrev <-> x <-> xNext
        xPrev.next = xNext;
        xNext.prev = xPrev;

        // null <-x -> null
        x.prev = x.next = null;
        size--;
        return x.element;
    }

    // 删除数组最前面的元素(返回删除的元素)
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        // 虚拟节点的存在是我们不用考虑空指针的问题
        Node<E> x = head.next;
        Node<E> temp = x.next;
        // head <-> x <-> temp
        head.next = temp;
        temp.prev = head;

        x.prev = x.next = null;
        // head <-> temp

        size--;
        return x.element;
    }

    /***** 查 *****/
    // 获取指定index的元素
    public E get(int index) {
        checkElementIndex(index);
        Node<E> x = getNode(index);
        return x.element;
    }

    // 获取数组最前面的元素
    public E getFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> first = head.next;
        return first.element;
    }

    // 获取数组最后一个元素
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> last = tail.prev;
        return last.element;
    }


    /***** 改 *****/
    // 在指定index位置替换元素
    public E set(int index, E e) {
        checkElementIndex(index);
        Node<E> temp = getNode(index);
        E oldVal = temp.element;
        temp.element = e;
        return oldVal;
    }

    /***** 工具函数 *****/
    // 获取数组的元素个数
    public int size() {
        return size;
    }

    // 是否为空
    private boolean isEmpty() {
        return size == 0;
    }

    // 检查索引是否越界
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 检查索引是否越界
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 根据index获取node
    private Node<E> getNode(int index) {
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.element + " -> ");
        }
        System.out.println("null");
    }



}
