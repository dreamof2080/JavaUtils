package com.jeffrey.util.algorithms;

/** 二位数组的行列转置
 * @author Jeffrey.Liu
 * @date 2018-6-13
 */
public class RevereArray {
    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int[][] array = new int[m][n];
        int value = 1;
        for (int i = 0; i < m; i++) {
            for (int i1 = 0; i1 < n; i1++) {
                array[i][i1] = value;
                System.out.println("array["+i+"]["+i1+"]="+value);
                value++;
            }
        }
        int[][] array2 = new int[n][m];
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 0; i1 < array[i].length; i1++) {
                array2[i1][i] = array[i][i1];
                System.out.println("array2["+i1+"]["+i+"]="+array[i][i1]);
            }
        }

    }
}
