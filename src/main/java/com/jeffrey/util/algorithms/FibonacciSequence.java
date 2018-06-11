package com.jeffrey.util.algorithms;

/** 输出斐波那契数列
 * @author Jeffrey.Liu
 * @date 2018-6-11
 */
public class FibonacciSequence {

    public void fibonacci(){
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }
}
