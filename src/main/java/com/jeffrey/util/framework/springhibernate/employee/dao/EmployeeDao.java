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

    void update(Employee employee);

    void delete(Employee employee);
}
