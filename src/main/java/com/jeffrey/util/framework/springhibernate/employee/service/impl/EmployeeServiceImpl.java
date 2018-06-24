package com.jeffrey.util.framework.springhibernate.employee.service.impl;

import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;
import com.jeffrey.util.framework.springhibernate.employee.dao.EmployeeDao;
import com.jeffrey.util.framework.springhibernate.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jeffrey.Liu
 * @date 2018-6-23
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public void printEmployeeById(Integer id) {
        Employee employee = employeeDao.getById(id);
        logger.info(employee.toString());
    }

    @Override
    public void insert(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

}
