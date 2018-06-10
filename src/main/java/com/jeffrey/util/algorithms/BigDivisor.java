package com.jeffrey.util.algorithms;

/**求两个数的最大公约数
 * @author Jeffrey.Liu
 * @date 2018-6-10
 */
public class BigDivisor {
    public static void main(String[] args) {
        int p = 31;
        int q = 21;
        System.out.println(gcd(p,q));
    }

    public static int gcd(int p,int q){
        if (q==0){
            return p;
        }
        int r = p % q;
        return gcd(q,r);
    }
}
