package com.jeffrey.util.algorithms;

/** 手动把整数转换成二进制
 *  Integer.toBinaryString(N)
 * @author Jeffrey.Liu
 * @date 2018-6-12
 */
public class ToBinaryString {

    public void to(){
        //这是我自己写的实现
        int somenum = 10;
        String str = "";
        while(true){
            int tmpnum = somenum % 2;
            int tmpnum2 = somenum / 2;
            if (somenum==1){
                str += "1";
                break;
            }else {
                str += "" + tmpnum;
                somenum = tmpnum2;
            }
        }
        char[] ch = str.toCharArray();
        for(int i = 0 ; i < ch.length/2 ; i++){
            char temp = ch[i];
            ch[i] = ch[ch.length-i-1];
            ch[ch.length-i-1] = temp;
        }
        System.out.println(new String(ch));

        //这是书上给出的实现
        somenum = 10;
        String s = "";
        for (int n=somenum;n>0;n/=2){
            s = (n % 2) + s;
        }
        System.out.println(s);
    }
}
