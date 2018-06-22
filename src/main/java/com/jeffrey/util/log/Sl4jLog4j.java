package com.jeffrey.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey.Liu
 * @date 2018-06-22 9:39
 **/
public class Sl4jLog4j {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Logger logger2 = LoggerFactory.getLogger(Sl4jLog4j.class);

    public void main(){
        logger.info("测试log,{}","这是占位符");
    }

    public static void main(String[] args) {
        logger2.info("测试log,{}","这是占位符");
    }
}
