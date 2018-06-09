package com.jeffrey.util.md5;

import org.junit.Test;


/**
 * @author Jeffrey.Liu
 * @date 2018-6-9
 */
public class SpringMd5Test {
    @Test
    public void test(){
        SpringMd5 springMd5 = new SpringMd5();
        String password = springMd5.getMD5("123456");
        System.out.println(password);
    }
}