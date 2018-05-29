package com.jeffrey.test.properties;

import com.jeffrey.util.properties.SpringAnnotation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试SpringAnnotation
 *
 * @author Jeffrey.Liu
 * @date 2018-05-29 14:06
 **/
public class TestSpringAnnotation {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-*.xml");
        SpringAnnotation springAnnotation = (SpringAnnotation) context.getBean("springAnnotationTest");
        System.out.println(springAnnotation.getOracleurl());
        System.out.println(springAnnotation.getRedisurl());
    }
}
