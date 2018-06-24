package com.jeffrey.util.framework.springhibernate.employee.dao.impl;

import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;
import com.jeffrey.util.framework.springhibernate.employee.dao.EmployeeDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Jeffrey.Liu
 * @date 2018-6-23
 */
@Repository
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAll() {
        CriteriaBuilder cb = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);
        cq = cq.select(employeeRoot);
        Query<Employee> query = this.currentSession().createQuery(cq);
        return query.list();
    }

    @Override
    public Employee getById(Integer id) {
        return this.currentSession().find(Employee.class,id);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        this.currentSession().saveOrUpdate(employee);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        this.currentSession().delete(employee);
    }
}
