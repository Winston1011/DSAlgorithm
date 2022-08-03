package dz.winston;

import java.util.NoSuchElementException;

/**
 * @Author: EpochDZ
 * @Date: 18:05 2022/8/2
 * @Description: 单链表
 * @Version v1.0
 */
public class MyLinkedList2<E> {
    // 单链表节点
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    // 当前元素个数
    private int size;
    // 双链表头节点、尾节点
    private final Node<E> head, tail;

    // 构造函数初始化头尾节点
    public MyLinkedList2() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        this.size = 0;
    }

    /*****  增 *****/
    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        // head -> temp -> tail
        head.next = x;
        x.next = temp;
        size++;
    }

    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp;
        if (size - 1 >= 0) {
            temp = getNode(size - 1);
        } else {
            temp = head;
        }
        // temp -> tail
        x.next = tail;
        temp.next = x;
        // temp -> x -> tail
        size++;
    }

    public void add(int index, E e) {
        checkPositionIndex(index);
        if(index == size){
            addLast(e);
            return;
        }
        if(index == 0){
            addFirst(e);
            return;
        }

        Node<E> x = new Node<>(e);
        Node<E> tempPrev = getNode(index - 1);
        x.next = tempPrev.next;
        tempPrev.next = x;

        size++;
    }

    /*****  删 *****/
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> first = head.next;
        // head -> first -> temp ...
        head.next = first.next;
        first.next = null;
        size--;
        return first.element;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> x = getNode(size - 1);
        Node<E> temp;
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
        return x.element;
    }

    public E remove(int index) {
        checkElementIndex(index);
        if (index == 0) {
            removeFirst();
        }
        Node<E> prev = getNode(index - 1);
        Node<E> temp = getNode(index);
        // prev -> temp -> next
        prev.next = temp.next;
        temp.next = null;
        // prev -> next

        size--;
        return temp.element;
    }

    /*****  改 *****/
    public E set(int index, E e) {
        checkElementIndex(index);
        Node<E> p = getNode(index);

        E oldVal = p.element;
        p.element = e;

        return oldVal;
    }

    /*****  查 *****/
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.element;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(size - 1).element;
    }

    public E get(int index) {
        checkElementIndex(index);
        Node<E> p = getNode(index);
        return p.element;
    }

    /*****  其他 *****/
    // 是否为空
    private boolean isEmpty() {
        return size == 0;
    }

    // 获取链表元素个数
    private int size() {
        return size;
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

    // 返回index对应的node
    private Node<E> getNode(int index) {
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.element + " -> ");
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        MyLinkedList2<Integer> linkedList = new MyLinkedList2<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addLast(3);
        linkedList.add(1,2);   //链表变为2-> 2 -> 1 -> 3
        linkedList.display();
        System.out.println(linkedList.get(1));            //返回2
        linkedList.remove(2);  //现在链表是1-> 3
        linkedList.get(1);            //返回3

        linkedList.display();
    }

}
