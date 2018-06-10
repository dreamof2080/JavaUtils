package com.jeffrey.util.algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/** 二分查找
 * @author Jeffrey.Liu
 * @date 2018-6-10
 */
public class BinarySearch {
    /**
     * 二分查找获取key在数组中的位置
     * @param key 需要查找的值
     * @param a 目标数组
     * @return key在数组a中的位置
     */
    public static int rank(int key,int[] a){
        int lo = 0;
        int hi = a.length - 1;
        while (lo<=hi){
            int mid = lo + (hi - lo) /2;
            if (key < a[mid]){
                hi = mid - 1;
            }else if (key > a[mid]){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            //打印key在数组中的位置
            StdOut.println(rank(key,whitelist));
        }
    }
}
