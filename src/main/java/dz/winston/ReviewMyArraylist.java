package dz.winston;

import java.util.Arrays;

/**
 * @Author: EpochDZ
 * @Date: 11:07 2022/8/4
 * @Description: arrayList
 * @Version v1.0
 */
public class ReviewMyArraylist {
    //实际数组数据
    private int[] data;
    //当前元素个数
    private int size;
    //初始默认容量
    private static final int DEFAULT_CAPACITY = 10;

    public ReviewMyArraylist() {
        this(DEFAULT_CAPACITY);
    }

    //初始化
    public ReviewMyArraylist(int defaultCapacity){
        data = new int[defaultCapacity];
        this.size = 0;
    }

    /****** 增 ******/
    // 在开头添加元素
    public void addFirst(int val) {
        if (size == DEFAULT_CAPACITY) {
            // 扩容+1
            reSize(size + 1);
        }
        //整体向后移一位
        System.arraycopy(data, 0, data, 1, size);
        data[0] = val;
        size++;
    }
    // 在结尾添加元素
    public void addLast(int val){
        if (size == DEFAULT_CAPACITY) {
            // 扩容+1
            reSize(size + 1);
        }
        // 末尾赋值
        data[size] = val;
        size++;
    }
    // 在指定index后添加元素
    public void add(int index, int val) {
        checkPositionIndex(index);
        if (size == DEFAULT_CAPACITY) {
            // 扩容+1
            reSize(size + 1);
        }
        // 从index开始到size-index整体向后移动一位
        System.arraycopy(data, index, data, index + 1, size - index);
        // index处赋值
        data[index] = val;
        size++;
    }

    /****** 删 ******/
    // 删除开头元素（返回删除值）
    public int removeFirst() {
        if (size <= data.length / 4) {
            //缩容一半
            reSize(data.length / 2);
        }
        int firstValue = data[0];
        //从第二个元素 整体向前移一位
        System.arraycopy(data, 1, data, 0, size - 1);
        size--;
        return firstValue;
    }

    // 删除结尾元素（返回删除值）
    public int removeLast(){
        if (size <= data.length / 4) {
            //缩容一半
            reSize(data.length / 2);
        }
        int lastValue = data[size - 1];
        data[size - 1] = 0;
        size--;
        return lastValue;
    }
    // 删除指定index中的元素（返回删除值）
    public int remove(int index) {
        checkElementIndex(index);
        if (size <= data.length / 4) {
            //缩容一半
            reSize(data.length / 2);
        }
        int oldValue = data[index];
        // 从index+1到size-index-1处整体往前移一位
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[size - 1] = 0;
        size--;
        return oldValue;
    }

    /****** 查 ******/
    // 返回开头元素值
    public int getFirst() {
        return data[0];
    }

    // 返回结尾元素值
    public int getLast() {
        return data[size - 1];
    }
    // 返回指定index中值
    public int get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    /****** 改 ******/
    // 更改指定index中的值
    public void set(int index, int val) {
        checkElementIndex(index);
        data[index] = val;
    }

    /****** 其他 ******/
    // 判断是否为空
    private boolean isEmpty(){
        return this.size == 0;
    }

    // 返回当前元素个数大小
    private int size(){
        return this.size;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
    }

    // 重扩容或者缩容
    private void reSize(int newCapacity) {
        int[] newData = new int[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void display() {
        System.out.println("size = " + size + " cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        // 初始容量设置为 10
        ReviewMyArraylist arr = new ReviewMyArraylist();

        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
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
