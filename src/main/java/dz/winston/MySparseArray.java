package dz.winston;

import javax.swing.*;

/**
 * @Author: EpochDZ
 * @Date: 19:53 2022/8/3
 * @Description:
 * @Version v1.0
 */
public class MySparseArray {
//    private int m;
//    private int n;
//    private int[][] twoDimArray = new int[m][n];

    private int validNum;
    private int[][] sparseArray = new int[validNum][3];


    public int[][] transToSparse(int[][] twoDimArr) {
        int sum = 0;
        for (int i = 0; i < twoDimArr.length; i++) {
            for (int j = 0; j < twoDimArr[0].length; j++) {
                if (twoDimArr[i][j] != 0) {
                    sum++;
                }
            }
        }
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

//    public int[][] transToTwoDim(int [][] sparseArr){
//
//
//    }

    public static void main(String[] args) {
        MySparseArray mySparseArray = new MySparseArray();
        int [][] chessArr = new int[5][5];
        chessArr[1][1] = 3;
        chessArr[2][2] = 5;
        chessArr[3][3] = 7;
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[0].length; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        int [][] sparseArr = mySparseArray.transToSparse(chessArr);
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);

        }

    }


}
