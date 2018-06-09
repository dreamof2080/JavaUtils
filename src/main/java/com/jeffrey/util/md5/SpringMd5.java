package com.jeffrey.util.md5;

import org.springframework.util.DigestUtils;

/**
 * @author Jeffrey.Liu
 * @date 2018-06-09
 */
public class SpringMd5 {
    private static final String SLAT = "sldkfj(*sdf23%&*jkj&&DD";

    private static String getMD5(String password){
        String base = password + "/" + SLAT;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public static void main(String[] args) {
        String password = "123456";
        System.out.println(getMD5(password));
    }
}
