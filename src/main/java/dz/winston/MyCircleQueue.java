package dz.winston;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: EpochDZ
 * @Date: 17:06 2022/8/5
 * @Description:
 * @Version v1.0
 */
public class MyCircleQueue {

    // 当前队列有效元素
    private int size;
    // 存放数据的ArrayList
    private final List<Integer> listQueue;
    // 初始默认大小
    private static final int DEFAULT_CAPACITY = 5;
    // 队列最大值
    private final int maxCapacity;

    public MyCircleQueue() {
        this(DEFAULT_CAPACITY);
    }

    public MyCircleQueue(int capacity) {
        listQueue = new ArrayList<>(capacity);
        this.maxCapacity = capacity;
        this.size = 0;
    }


    public void enQueue(int value) {
        if (isFull()) {
            System.out.println("队列已满,无法enQueue");
            return;
        }
        if (isEmpty()) {
            size++;
            listQueue.add(value);
            return;
        }
        listQueue.add(value);
        size++;
    }

    public void deQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法deQueue");
            return;
        }
        listQueue.remove(0);
        size--;
    }

    public int Front() {
        if (isEmpty()) {
            System.out.println("队列为空，队列头为-1");
            return -1;
        }
        return listQueue.get(0);
    }

    public int Rear() {
        if (isEmpty()) {
            System.out.println("队尾为空，队列头为-1");
            return -1;
        }
        return listQueue.get(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断队列是否已满
    public boolean isFull() {
        return size == maxCapacity;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        System.out.println(listQueue.toString());
    }


    public static void main(String[] args) {
        MyCircleQueue myCircleQueue = new MyCircleQueue();

        System.out.println(myCircleQueue.Front());

        myCircleQueue.enQueue(3);
        myCircleQueue.display();
        myCircleQueue.enQueue(4);
        myCircleQueue.enQueue(5);
        myCircleQueue.enQueue(6);
        myCircleQueue.enQueue(7);
        myCircleQueue.enQueue(8);
//        myCircleQueue.display();

        myCircleQueue.deQueue();
        myCircleQueue.display();
        myCircleQueue.deQueue();
        myCircleQueue.display();
        myCircleQueue.enQueue(9);
        myCircleQueue.display();

        System.out.println(myCircleQueue.Front());
        System.out.println(myCircleQueue.Rear());


    }
}

