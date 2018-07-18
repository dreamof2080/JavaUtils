package com.jeffrey.util.yaml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

/**
 * @author Jeffrey.Liu
 * @date 2018-07-18 15:59
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-base.xml"})
public class YamlTest {
    @Autowired
    @Qualifier("yamlProperties")
    private Properties yamlProperties;

    @Test
    public void test(){
        System.out.println(yamlProperties.getProperty("form.class"));
        System.out.println(yamlProperties.get("workflow.name"));
    }
}
