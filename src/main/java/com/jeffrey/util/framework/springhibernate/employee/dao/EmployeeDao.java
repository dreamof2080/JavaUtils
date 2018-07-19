package com.jeffrey.util.framework.springhibernate.employee.dao;

import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取年龄最大的employee max(age)
     */
    List<Employee> getByMaxAge(Integer age);

    /**
     * 模糊查询 where a like '%'||b||'%'
     */
    List<Employee> getBySomeName(String pattern);

    /**
     * 查询某个字段 select name from employee where id=?
     */
    List<String> getNameById(Integer id);

    /**
     * 查询多个字段
     */
    List<Tuple> getMultiById(Integer id);

    /**
     * 连接两个表进行查询 from employee,department
     */
    List<Tuple> getFromTwoTables(String deptid);

    /**
     *  where a not in (select b from c)
     */
    List<Employee> getNotIn(String deptname);

    /**
     * where (a=1 or a=2 or a=3) and b=1
     */
    List<Employee> getMultiOr();

    /**
     * createNativeQuery使用：select id from employee
     */
    List<Integer> getByNativeSQL();

    /**
     * createNativeQuery使用：select * from employee
     */
    List<Object[]> getByNativeSQL2();
}
