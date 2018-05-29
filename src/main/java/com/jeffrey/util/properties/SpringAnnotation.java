package com.jeffrey.util.properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过spring注解的方式获取
 * @since 3.0 需要spring3.0及以上才支持@Value注解
 * @author Jeffrey.Liu
 * @date 2018-05-29 13:41
 **/
@Component("springAnnotationTest")
public class SpringAnnotation {
    @Value("${oracle.username}")
    private String oracleurl;
    @Value("${redis.host}")
    private String redisurl;

    public String getOracleurl() {
        return oracleurl;
    }

    public String getRedisurl() {
        return redisurl;
    }
}
