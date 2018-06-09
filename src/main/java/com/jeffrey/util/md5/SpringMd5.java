package com.jeffrey.util.md5;

import org.springframework.util.DigestUtils;

/**
 * @author Jeffrey.Liu
 * @date 2018-06-09
 */
public class SpringMd5 {
    private final String SLAT = "sldkfj(*sdf23%&*jkj&&DD";

    public String getMD5(String password){
        String base = password + "/" + SLAT;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
