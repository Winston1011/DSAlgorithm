package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 21:17 2022/8/25
 * @Description:
 * @Version v1.0
 */
public class MyRecursiveDemo {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};

        // 正序递归打印
        printArr(arr);
        // 逆序递归打印
        printArrReverse(arr);
    }

    private static void printArr(int[] arr) {

        // 打印从arr[0]...往后的元素
        printArr(arr, 0);
    }

    // 打印从arr[i]...往后的元素
    private static void printArr(int[] arr, int i) {
        // base case
        if (i == arr.length) {
            return;
        }
        // 打印
        System.out.println(arr[i]);
        // 打印从arr[i+1]...往后的元素
        printArr(arr, i + 1);
    }


    private static void printArrReverse(int[] arr) {

        printArrReverse(arr, arr.length - 1);
    }

    // 打印从arr[i]...往前的元素
    private static void printArrReverse(int[] arr, int i) {
        if (i < 0) {
            return;
        }
        System.out.println(arr[i]);

        // 打印从arr[i - 1]...往前的元素
        printArrReverse(arr, i - 1);

    }


}
