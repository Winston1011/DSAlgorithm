package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 14:31 2022/8/19
 * @Description: 回顾复习-自设计ringbuffer（环形队列，可存储和读取多字节）
 * @Version v1.0
 */
public class ReviewMyRingBuffer {
    // 队列数组
    private byte[] ringBuffer;
    // 当前字节元素大小
    private int size;
    // 默认大小
    private final static int DEFAULT_CAPACITY = 1024;
    // 头、尾指针
    private int r, w;
    // mask，防越界作用
    private int mask;

    // 构造默认大小的对象
    public ReviewMyRingBuffer() {
        this(DEFAULT_CAPACITY);
    }

    // 构造函数
    public ReviewMyRingBuffer(int cap) {
        int newCap = ceilToPowerOfTwo(cap);
        ringBuffer = new byte[newCap];
        this.r = this.w = 0;
        this.mask = cap - 1;
        this.size = 0;
    }

    // 读取字符(读取到out数组）
    public int read(byte[] out) {
        if (out == null || out.length == 0 || isEmpty())
            return 0;
        // 读取的字节数
        int n = Math.min(size, out.length);

        // 情况1： r----w
        if (w > r) {
            // r----w 读取后变成 **r--w
            // copy data[r..r+n] to out[0..]
            System.arraycopy(ringBuffer, r, out, 0, n);
            // 向前移动读指针
            r += n;
            // 可读取的字节数减少了 n
            size -= n;
            return n;
        }

        // 情况2：--w  r---
        if (r + n <= ringBuffer.length) {
            // 情况2.1：--w  r--- 读取后变成 --w  **r-
            // copy data[r..r+n] to out[0..]
            System.arraycopy(ringBuffer, r, out, 0, n);
        } else {
            // 情况2.2：----w  r-- 读取后变成  *r--w  ***
            int n1 = ringBuffer.length - r;
            int n2 = n - n1;
            // copy data[r..] to out[0..n1]
            System.arraycopy(ringBuffer, r, out, 0, n1);
            // copy data[0..n2] to out[n1..]
            System.arraycopy(ringBuffer, 0, out, n1, n2);
        }

        // 向前移动读指针
        r = (r + n) & mask;

        // 可读取的字节数减少了 n
        size -= n;

        return n;
    }

    // 写入字符(从in byte数组写入ringbuffer）
    public int write(byte[] in) {
        int n = in.length;
        // 查询可存储的空间大小，判断是否需要扩容
        int free = ringBuffer.length - size;
        if (n > free) {
            // 确保 buffer 容量足够
            ensureCapacity(size + n);
        }
        // 写入（分两种情况）
        //1、..r.....w..
        if (w >= r) {
            //1.1、..r.....**w
            if (w + n < ringBuffer.length) {
                System.arraycopy(in, 0, ringBuffer, w, n);
            } else {
                //1.2、***w...r....***
                int n1 = ringBuffer.length - w;
                int n2 = n - n1;
                System.arraycopy(in, 0, ringBuffer, w, n1);
                System.arraycopy(in, n1, ringBuffer, 0, n2);
            }
        } else {
            //2、..w.....r..
            // buffer 容量肯定足够，所以直接 copy 就行了
            System.arraycopy(in, 0, ringBuffer, w, n);
        }
        // w向后移动n个位置
        w = (w + n) & mask;
        // size += n
        size += n;
        return n;
    }

    // 是否为空
    private boolean isEmpty() {
        return size == 0;
    }

    // 扩容(先确定最小2的指数的容量大小，读取原buffer中字符个数并输出到temp，新buffer等于temp）
    private void ensureCapacity(int newCap) {
        int newCapacity = ceilToPowerOfTwo(newCap);
        byte[] temp = new byte[newCapacity];
        int n = read(temp);
        // 更新其他字段的值
        this.ringBuffer = temp;
        this.r = 0;
        this.w = n;
        this.mask = newCapacity - 1;
        this.size += n;
    }

    // 返回最小2的指数的值
    private int ceilToPowerOfTwo(int n) {
        if (n < 0) {
            // 肯定不能小于 0
            n = 2;
        }

        if (n > (1 << 30)) {
            // int 型最大值为 2^31 - 1
            // 所以无法向上取整到 2^31
            n = 1 << 30;
        }
        int res = 1;
        while (res < n) {
            res = res * 2;
        }
        return res;
    }

    public static void main(String[] args) {
        ReviewMyRingBuffer reviewMyRingBuffer = new ReviewMyRingBuffer(3);

        String s = "ABCDEFGHIJKLMN";
        int nwrite = reviewMyRingBuffer.write(s.getBytes());
        System.out.println("write " + nwrite + " bytes " + s);

        byte[] out = new byte[9];
        int nread = reviewMyRingBuffer.read(out);
        System.out.println("read " + nread + " bytes " + new String(out));

        byte[] out2 = new byte[9];
        nread = reviewMyRingBuffer.read(out2);
        System.out.println("read " + nread + " bytes " + new String(out2));

        //write 14 bytes ABCDEFGHIJKLMN
        //read 9 bytes ABCDEFGHI
        //read 5 bytes JKLMN    

        String s1 = "OPQRSTUVWXYZ";
        int nwrite1 = reviewMyRingBuffer.write(s1.getBytes());
        System.out.println("write " + nwrite1 + " bytes " + s1);

        String s2 = "winston is me";
        int nwrite2 = reviewMyRingBuffer.write(s2.getBytes());
        System.out.println("write " + nwrite2 + " bytes " + s2);

    }

}
