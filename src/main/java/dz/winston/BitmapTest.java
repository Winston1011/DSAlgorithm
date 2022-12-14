package dz.winston;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Winston DZ
 * @E-mail 1071442894@qq.com
 * @date 2021/12/24
 **/
public class BitmapTest {

    private static final int N = 10000000;

    private int[] a = new int[N / 32 + 1];

    /**
     * 设置所在的bit位为1
     *
     * @param n
     */
    public void addValue(int n) {
        //row = n / 32 求十进制数在数组a中的下标
        int row = n >> 5;
        //相当于 n % 32 求十进制数在数组a[i]中的下标
        a[row] |= 1 << (n & 0x1F);
    }

    // 判断所在的bit为是否为1
    public boolean exists(int n) {
        int row = n >> 5;
        return (a[row] & (1 << (n & 0x1F))) != 1;
    }

    public void display(int row) {
        System.out.println("BitMap位图展示");
        for (int i = 0; i < row; i++) {
            List<Integer> list = new ArrayList<Integer>();
            int temp = a[i];
            for (int j = 0; j < 32; j++) {
                list.add(temp & 1);
                temp >>= 1;
            }
            System.out.println("a[" + i + "]" + list);
        }
    }

    public static void main(String[] args) {
        int num[] = {1, 5, 30, 32, 64, 56, 159, 120, 21, 17, 35, 45};
        BitmapTest map = new BitmapTest();
        for (int i = 0; i < num.length; i++) {
            map.addValue(num[i]);
        }

        int temp = 120;
        if (map.exists(temp)) {
            System.out.println("temp:" + temp + " has already exists");
        }
        map.display(5);
    }
}
