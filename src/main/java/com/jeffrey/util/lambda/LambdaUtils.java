package com.jeffrey.util.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lambda语法
 *
 * @author Jeffrey.Liu
 * @date 2018-09-16 17:47
 **/
public class LambdaUtils {
    /**
     * list方法的lambda写法
     */
    public static void list(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.forEach(System.out::println);
    }

    /**
     * map方法的lambda写法
     */
    public static void map(){
        Map<String,String> map = new HashMap<>();
        map.put("k1","11");
        map.put("k2","22");
        map.forEach((k,v)->{
            System.out.println("kee:"+k+",value:"+v);
        });
    }

    public static void main(String[] args) {
        list();
    }
}
