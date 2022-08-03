package dz.winston;

/**
 * @Author: EpochDZ
 * @Date: 19:53 2022/8/3
 * @Description:
 * @Version v1.0
 */
public class MySparseArray {

    /**
     * 获取有效数字个数
     * @param twoDimArr 二维数组
     * @return int
     */
    public int getValidNum(int[][] twoDimArr) {
        int sum = 0;
        for (int i = 0; i < twoDimArr.length; i++) {
            for (int j = 0; j < twoDimArr[0].length; j++) {
                if (twoDimArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 二维数组转稀疏数组
     * @param twoDimArr 二维数组
     * @return int[][]
     */
    public int[][] transToSparse(int[][] twoDimArr) {
        int sum = getValidNum(twoDimArr);
        int[][] sparseArr = new int[sum + 1][3];
        int count = 0;
        for (int i = 0; i < twoDimArr.length; i++) {
            for (int j = 0; j < twoDimArr[0].length; j++) {
                if (twoDimArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = twoDimArr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     * @param sparseArr 稀疏数组
     * @return int[][]
     */
    public int[][] transToTwoDim(int [][] sparseArr){
        int m = sparseArr[0][0];
        int n = sparseArr[0][1];
        int[][] twoDimArr = new int[m][n];
        for (int i = 1; i < sparseArr.length; i++) {
            twoDimArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return twoDimArr;
    }

    public static void main(String[] args) {
        MySparseArray mySparseArray = new MySparseArray();
        int [][] twoDimArr = new int[5][5];
        twoDimArr[1][1] = 3;
        twoDimArr[2][2] = 5;
        twoDimArr[3][3] = 7;
        int sum = mySparseArray.getValidNum(twoDimArr);
        int [][] sparseArr = mySparseArray.transToSparse(twoDimArr);
        sparseArr[0][0] = twoDimArr.length;
        sparseArr[0][1] = twoDimArr[0].length;
        sparseArr[0][2] = sum;

        System.out.println("------------------二维数组转稀疏数组------------------");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\n", ints[0], ints[1], ints[2]);
        }

        System.out.println("------------------稀疏数组转二维数组------------------");
        int[][] transTwoDimArr = mySparseArray.transToTwoDim(sparseArr);
        for (int[] cells:transTwoDimArr) {
            for (int cell: cells ) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

    }



}
