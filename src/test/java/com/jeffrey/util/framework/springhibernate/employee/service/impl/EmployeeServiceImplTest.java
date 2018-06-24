package com.jeffrey.util.framework.springhibernate.employee.service.impl;

import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;
import com.jeffrey.util.framework.springhibernate.employee.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jeffrey.Liu
 * @date 2018-6-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-base.xml"})
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void printEmployeeById() {
        int id = 1;
        employeeService.printEmployeeById(id);
    }

    @Test
    public void testInsert(){
        Employee employee = new Employee();
        employee.setAge(10);
        employee.setName("123");
        employeeService.insert(employee);
    }

    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(9);
        employee.setAge(30);
        employee.setName("123");
        employeeService.update(employee);
    }

    @Test
    public void testDelete(){
        Employee employee = new Employee();
        employee.setId(9);
        employeeService.delete(employee);
    }
}