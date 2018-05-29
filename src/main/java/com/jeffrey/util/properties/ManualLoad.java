package com.jeffrey.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 手动加载properties文件
 *
 * @author Jeffrey.Liu
 * @date 2018-05-29 16:37
 **/
public class ManualLoad {
    private static Properties oracleProps=new Properties();
    private static Properties redisProps=new Properties();

    static {
        String oracle = ManualLoad.class.getClassLoader().getResource("oraclejdbc.properties").getPath();
        String redis = ManualLoad.class.getClassLoader().getResource("redis.properties").getPath();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(new File(oracle)), "utf-8");
            oracleProps.load(read);
            read = new InputStreamReader(new FileInputStream(new File(redis)), "utf-8");
            redisProps.load(read);
        } catch (IOException e) {
            System.out.println("Load document.properties、rtx.properties file failed! Exception is:" + e);
        }
    }

    public static Properties getOracleProps() {
        return oracleProps;
    }

    public static Properties getRedisProps() {
        return redisProps;
    }
}
