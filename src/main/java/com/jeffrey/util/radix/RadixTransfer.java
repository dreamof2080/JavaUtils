package com.jeffrey.util.radix;

/**
 * 二进制、十进制、十六进制之间的转换
 *
 * @author Jeffrey.Liu
 * @date 2018-05-30 8:47
 **/
public class RadixTransfer {
    public static void main(String[] args) {
        //十进制转为二进制
        System.out.println(Integer.toBinaryString(112));
        //十进制转为十六进制
        System.out.println(Integer.toHexString(112));
        //十进制转为八进制
        System.out.println(Integer.toOctalString(112));

        //二进制转十进制
        System.out.println(Integer.parseInt("111001",2));
        //八进制转十进制
        System.out.println(Integer.parseInt("27",8));
        //十六进制转十进制
        System.out.println(Integer.parseInt("A8",16));
    }
}
