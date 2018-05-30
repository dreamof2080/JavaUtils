package com.jeffrey.util.bytes;

import java.io.Serializable;

/**
 * 雇员对象，用于Object2ByteAndFile
 *
 * @author Jeffrey.Liu
 * @date 2018-05-30 8:55
 **/
public class Employee implements Serializable {
    private int age;
    private String name;
    private String job;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
