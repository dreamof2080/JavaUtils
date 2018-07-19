package com.jeffrey.util.framework.springhibernate.employee.dao.impl;

import com.jeffrey.util.framework.springhibernate.employee.dao.EmployeeDao;
import com.jeffrey.util.framework.springhibernate.employee.entity.Department;
import com.jeffrey.util.framework.springhibernate.employee.entity.Employee;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey.Liu
 * @date 2018-6-23
 */
@Repository
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

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
    public void saveOrUpdate(Employee employee) {
        this.currentSession().saveOrUpdate(employee);
    }

    @Override
    public void delete(Employee employee) {
        this.currentSession().delete(employee);
    }

    @Override
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

    @Override
    public List<String> getNameById(Integer id) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root.<String>get("name"));
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),id));
        Query<String> query = this.currentSession().createQuery(criteriaQuery.where(predicate));
        return query.list();
    }

    @Override
    public List<Tuple> getMultiById(Integer id) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.multiselect(root.<String>get("name"),root.<Integer>get("age"));
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),id));
        Query<Tuple> query = this.currentSession().createQuery(criteriaQuery.where(predicate));
        return query.list();
    }

    @Override
    public List<Tuple> getFromTwoTables(String deptid) {
        //这种方法生成的sql语句是cross join,当表的数据量大的时候效率很慢
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        Root<Employee> root = criteriaQuery.from(Employee.class);
        Root<Department> root2 = criteriaQuery.from(Department.class);

        criteriaQuery = criteriaQuery.multiselect(root.get("name"),root2.get("name"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("deptid"),root2.get("id"))));
        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("age"),20)));
        Query query = this.currentSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0])));

        List<Tuple> list = query.list();
        for (Tuple tuple : list) {
            String deployeeName = (String) tuple.get(0);
            String deptName = (String)tuple.get(1);
            System.out.println(deployeeName+"/"+deptName);
        }
        return null;
    }

    @Override
    public List<Employee> getNotIn(String deptname) {
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();

        Subquery<String> subquery = criteriaQuery.subquery(String.class);
        Root<Department> subroot = subquery.from(Department.class);
        subquery.select(subroot.<String>get("id"));
        subquery.where(criteriaBuilder.equal(subroot.get("name"),deptname));
        predicates.add(criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.in(root.get("deptid")).value(subquery))));
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0])));
        return query.list();
    }

    @Override
    public List<Employee> getMultiOr() {
        List<Integer> datalist = new ArrayList<>();
        datalist.add(1);
        datalist.add(2);
        datalist.add(3);
        CriteriaBuilder criteriaBuilder = this.currentSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery = criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        for (Integer integer : datalist) {
            predicates.add(criteriaBuilder.equal(root.get("id"),integer));
        }
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.or(predicates.toArray(new Predicate[]{})),criteriaBuilder.equal(root.get("name"), "test"));
        Query<Employee> query = this.currentSession().createQuery(criteriaQuery.where(predicate));
        return query.list();
    }

    @Override
    public List<Integer> getByNativeSQL() {
        String sql = "select name from employee where id=?";
        NativeQuery nativeQuery = this.currentSession().createNativeQuery(sql).setParameter(1,100);
        List resultList = nativeQuery.getResultList();
        if (!resultList.isEmpty()){
            for (Object o : resultList) {
                System.out.println((String)o);
            }
        }
        return resultList;
    }

    @Override
    public List<Object[]> getByNativeSQL2() {
        String sql = "select * from employee where id=?";
        NativeQuery nativeQuery = this.currentSession().createNativeQuery(sql).setParameter(1,100);
        List<Object[]> resultList = nativeQuery.getResultList();
        if (!resultList.isEmpty()){
            for (Object[] objects : resultList) {
                for (Object object : objects) {
                    System.out.println(object);
                }
            }
        }
        return resultList;
    }
}
