package com.jeffrey.util.framework.springhibernate.employee.dao.impl;

import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;
import com.jeffrey.util.framework.springhibernate.employee.dao.EmployeeDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.*;
import java.util.ArrayList;
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
    public void saveOrUpdate(Employee employee) {
        this.currentSession().saveOrUpdate(employee);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        this.currentSession().delete(employee);
    }

    @Override
    @Transactional
    public List<Employee> getByName(String name){
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("name"), name));
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicate));
        return query.list();
    }

    @Override
    public List<Employee> getByName(String name, String orderField) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("name"), name));
        Order order = criteriaBuilder.asc(root.get(orderField));
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicate).orderBy(order));
        return query.list();
    }

    @Override
    public List<Employee> getByName(String name, String[] orderFields) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("name"), name));
        List<Order> orders = new ArrayList<>();
        for (String orderField : orderFields) {
            orders.add(criteriaBuilder.asc(root.get(orderField)));
        }
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicate).orderBy(orders));
        return query.list();
    }

    @Override
    public List<Employee> getByNameAndAge(String name, Integer age) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("name"), name)));
        predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("age"), age)));
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0])));
        return query.list();
    }

    @Override
    public List<Employee> getByMaxAge(Integer age) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        Expression maxExpr = root.get("age");
        criteriaQuery = criteriaQuery.select(criteriaBuilder.max(maxExpr));

        Query<Employee> query = this.currentSession().createQuery(criteriaQuery);
        return query.list();
    }

    @Override
    public List<Employee> getBySomeName(String pattern) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        Expression<String> expression = root.get("name");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(expression), "%"+pattern.toUpperCase()+"%"));
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicate));
        return query.list();
    }
}
