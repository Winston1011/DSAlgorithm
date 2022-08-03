package dz.winston;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author: EpochDZ
 * @Date: 16:44 2022/8/2
 * @Description:
 * @Version v1.0
 */
public class MyArrayList<E> implements Iterable<E> {
    // 定义数组
    private E[] data;
    // 当前元素个数
    private int size;
    //默认初始化容量为10
    private static final int DEFAULT_CAPACITY = 10;

    // 初始化
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // 构造函数
    public MyArrayList(int defaultCapacity) {
        data = (E[]) new Object[defaultCapacity];
        size = 0;
    }

    // 向数组中最后添加元素
    public void addLast(E e) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[size] = e;
        size++;
    }

    // 在对应index中位置添加element
    public void add(int index, E e) {
        // 检查索引越界
        checkPositionIndex(index);
        int cap = data.length;
        // 看 data 数组容量够不够
        if (size == cap) {
            resize(2 * cap);
        }
        // 搬移数据 data[index..] -> data[index+1..]
        System.arraycopy(data, index,
                data, index + 1,
                size - index);
        // 插入
        data[index] = e;
        size++;
    }

    // 在数组最前面添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 删除最后一个元素(返回删除的元素)
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        // 如果size小于data.length/4,则缩小数组的容量
        if (size < data.length / 4) {
            resize(data.length / 2);
        }
        E deletedVal = data[size - 1];
        data[size - 1] = null;
        size--;
        return deletedVal;
    }

    // 删除对应index位置的element
    public E remove(int index) {
        // 检查索引越界
        checkElementIndex(index);
        int cap = data.length;
        // 可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E deletedVal = data[index];
        // 搬移数据 data[index+1..] -> data[index..]
        System.arraycopy(data, index + 1,
                data, index,
                size - index - 1);

        data[size - 1] = null;
        size--;

        return deletedVal;
    }

    // 删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 返回对应index位置的元素
    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    // 设置对应index元素为新element，并返回之前的element
    public E set(int index, E element) {
        checkElementIndex(index);
        E oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 返回当前元素个数
    public int size() {
        return size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    // resize数组(扩容或缩容)
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
//        for (int i = 0; i < size; i++) {
//            newData[i] = data[i];
//        }
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int p = 0;

            @Override
            public boolean hasNext() {
                return p != size;
            }

            @Override
            public E next() {
                return data[p++];
            }
        };
    }

    private void display() {
        System.out.println("size = " + size + " cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }


    public static void main(String[] args) {
        // 初始容量设置为 3
        MyArrayList<Integer> arr = new MyArrayList<>();

        // 添加 5 个元素
        for (int i = 1; i <= 10; i++) {
            arr.addLast(i);
        }
        arr.display();
        arr.remove(3);
        arr.display();
        arr.add(1, 9);
        arr.display();
        arr.addFirst(100);
        arr.display();
        int val = arr.removeLast();
        arr.display();
    }
}
