package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 20:14 2022/8/16
 * @Description: 数组实现双端队列(循环队列，不可扩容）
 * @Version v1.0
 */
public class MyArrayDeque1 {
    // 数组队列
    private int[] arrQueue;
    // 当前元素大小
    private int size;
    // 默认队列大小
    private static final int DEFAULT_CAPACITY = 5;
    // 队头、队尾指针
    private int front, rear;

    //构造器
    public MyArrayDeque1() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayDeque1(int cap) {
        arrQueue = new int[cap];
        size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // 队尾新增元素
    public void addRear(int val) {

        if (isFull()) {
            throw new IndexOutOfBoundsException("队列已满,无法新增尾部元素");
        }
        arrQueue[rear % arrQueue.length] = val;
        rear++;
        size++;
    }

    // 队头弹出元素
    public int popFront() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空,无法弹出队头元素");
        }
        int oldValue = arrQueue[front % arrQueue.length];
        arrQueue[front % arrQueue.length] = -1;
        front++;
        size--;
        return oldValue;
    }

    // 队头新增元素
    public void addFront(int val) {
        if (isEmpty()) {
            arrQueue[front % arrQueue.length] = val;
            front++;
            size++;
            return;
        }
        if (isFull()) {
            throw new IndexOutOfBoundsException("对列已满，无法队头新增元素");
        }
        arrQueue[(front - 1) % arrQueue.length] = val;
        front--;
        size++;
    }

    // 队尾弹出元素
    public int popRear() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空，无法弹出队尾元素");
        }
        int oldValue = arrQueue[(rear - 1) % arrQueue.length];
        arrQueue[(rear - 1) % arrQueue.length] = -1;
        rear--;
        size--;
        return oldValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arrQueue.length;
    }

    public static void main(String[] args) {
        MyArrayDeque1 myArrayDeque1 = new MyArrayDeque1();
        myArrayDeque1.addRear(11);
        myArrayDeque1.addRear(22);
        myArrayDeque1.addRear(33);
        myArrayDeque1.addRear(44);
        myArrayDeque1.addRear(55);

        myArrayDeque1.popFront();
        myArrayDeque1.popFront();
        myArrayDeque1.popFront();


        myArrayDeque1.addRear(66);
        myArrayDeque1.addRear(77);

        myArrayDeque1.popFront();
        myArrayDeque1.popFront();
        myArrayDeque1.popFront();

        myArrayDeque1.popFront();
        myArrayDeque1.addRear(100);
        myArrayDeque1.addRear(101);
        myArrayDeque1.addRear(102);

        myArrayDeque1.popFront();
        myArrayDeque1.popFront();

        myArrayDeque1.addFront(88);
        myArrayDeque1.addFront(99);
        myArrayDeque1.addFront(101);
        myArrayDeque1.addFront(102);

        int ret7 = myArrayDeque1.popFront();
        int ret = myArrayDeque1.popFront();

        myArrayDeque1.popRear();
        myArrayDeque1.popRear();

        myArrayDeque1.addRear(5555);


    }


}

