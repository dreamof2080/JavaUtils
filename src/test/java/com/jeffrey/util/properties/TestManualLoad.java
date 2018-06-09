package com.jeffrey.util.properties;

import com.jeffrey.util.properties.ManualLoad;
import org.junit.Test;

import java.util.Properties;

/**
 * 测试手动加载
 *
 * @author Jeffrey.Liu
 * @date 2018-05-29 16:42
 **/
public class TestManualLoad {
    @Test
    public void test(){
        Properties oracle = ManualLoad.getOracleProps();
        Properties redis = ManualLoad.getRedisProps();
        System.out.println(oracle.getProperty("oracle.username"));
        System.out.println(redis.getProperty("redis.host"));
    }
}
