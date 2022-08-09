package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 14:54 2022/8/9
 * @Description: 自设计链表实现队列
 * @Version v1.0
 */
public class MyLinkedQueue<E> {
    private final MyLinkedList<E> listQueue = new MyLinkedList<E>();

    // 从队尾添加元素
    public void offer(E e) {
        listQueue.addLast(e);
    }

    // 从队头弹出元素
    public E poll() {
        return listQueue.removeFirst();
    }

    // 查看队头元素
    public E peekFirst() {
        return listQueue.getFirst();
    }

    // 查看队尾元素
    public E peekLast() {
        return listQueue.getLast();
    }

    public static void main(String[] args) {
        MyLinkedQueue<Integer> myLinkedQueue = new MyLinkedQueue<>();
        myLinkedQueue.offer(1);
        myLinkedQueue.offer(3);
        myLinkedQueue.offer(4);
        myLinkedQueue.offer(6);
        myLinkedQueue.listQueue.display();

        myLinkedQueue.poll();
        myLinkedQueue.listQueue.display();

        System.out.println(myLinkedQueue.peekFirst());
        System.out.println(myLinkedQueue.peekLast());

    }
}
