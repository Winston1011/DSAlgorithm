package dz.winston;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @Author: EpochDZ
 * @Date: 14:54 2022/8/9
 * @Description:
 * @Version v1.0
 */
public class MyArrayQueue {

    // 数组存放元素
    private final int[] arrQueue;
    // 当前队列元素个数
    private int size;
    // 默认队列大小
    private final static int DEFAULT_CAPACITY = 10;
    // 指向队头、队尾的指针
    private int front;
    private int rear;

    //默认构造一定大小的队列
    public MyArrayQueue() {
        this(DEFAULT_CAPACITY);
    }


    // 构造函数
    public MyArrayQueue(int capacity) {
        arrQueue = new int[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // 从队尾新增元素
    public void offer(int val) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("队列已满");
        }
        arrQueue[rear] = val;
        rear++;
        if (rear == arrQueue.length) {
            rear = 0;
        }
        size++;
    }

    // 从队头取出元素
    public int poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空");
        }
        int val = arrQueue[front];
        arrQueue[front] = -1;
        front++;
        if (front == arrQueue.length) {
            front = 0;
        }
        size--;
        return val;
    }

    //获取队尾元素
    public int peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空");
        }
        // last 是下一次应该添加元素的索引
        // first----last, [first, last)
        if (rear == 0) return arrQueue[arrQueue.length - 1];
        return arrQueue[rear - 1];
    }

    // 获取队头元素
    public int peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空");
        }
        return arrQueue[front];
    }

    // 是否已满
    private boolean isFull() {
        return size == arrQueue.length;
    }

    // 是否为空
    private boolean isEmpty() {
        return size == 0;
    }

    public void display(){
        System.out.println(Arrays.toString(arrQueue));
    }

    public static void main(String[] args) {
        MyArrayQueue myArrayQueue = new MyArrayQueue(5);
        myArrayQueue.offer(1);
        System.out.println(myArrayQueue.peekLast());
        myArrayQueue.offer(4);
        myArrayQueue.offer(8);
        myArrayQueue.offer(3);
        myArrayQueue.offer(9);
        myArrayQueue.display();


        myArrayQueue.poll();
        myArrayQueue.display();

        myArrayQueue.offer(12);
        myArrayQueue.display();
        System.out.println(myArrayQueue.peekLast());
        System.out.println(myArrayQueue.peekFirst());

    }
}
