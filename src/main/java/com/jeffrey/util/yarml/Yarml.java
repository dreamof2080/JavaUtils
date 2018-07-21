package com.jeffrey.util.yarml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Properties;

/**
 * 获取yaml资源文件
 * @author Jeffrey.Liu
 * @date 2018-07-18 15:50
 **/
public class Yarml {
    @Autowired
    @Qualifier("yamlProperties")
    private Properties yamlProperties;
}
