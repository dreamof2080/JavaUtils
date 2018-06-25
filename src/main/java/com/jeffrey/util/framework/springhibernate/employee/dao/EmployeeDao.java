package com.jeffrey.util.framework.springhibernate.employee.dao;

import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;

import java.util.List;

/**
 * @author Jeffrey.Liu
 * @date 2018-6-23
 */
public interface EmployeeDao {
    /**
     * 获取所有的employee对象
     * @return
     */
    List<Employee> getAll();

    /**
     * 根据ID获取employee对象
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 插入或更新
     * @param employee
     */
    void saveOrUpdate(Employee employee);

    /**
     *  删除
     * @param employee
     */
    void delete(Employee employee);

    /**
     * 根据名字查询 where name=?
     */
    List<Employee> getByName(String name);

    /**
     * 根据名字查询并根据年龄排序 where name=? order by age
     */
    List<Employee> getByName(String name,String orderField);

    /**
     * 根据名字查询并根据年龄、姓名排序 where name=? order by age,name
     */
    List<Employee> getByName(String name,String[] orderFields);

    /**
     * 根据名字和年龄查询 where name=? or age=?
     */
    List<Employee> getByNameAndAge(String name,Integer age);


}