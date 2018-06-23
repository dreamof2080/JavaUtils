package com.jeffrey.util.framework.employee.service;

import com.jeffrey.util.framework.employee.entity.Employee;

/**
 * @author Jeffrey.Liu
 * @date 2018-6-23
 */
public interface EmployeeService {

    /**
     * 打印信息
     * @param id
     */
    void printEmployeeById(Integer id);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
}
