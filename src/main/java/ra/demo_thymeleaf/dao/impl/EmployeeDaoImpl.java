package ra.demo_thymeleaf.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.demo_thymeleaf.dao.IEmployeeDao;
import ra.demo_thymeleaf.entity.Employee;

import java.util.Collections;
import java.util.List;
@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployees() {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from Employee").list();
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(Integer empId) {
        Session session = sessionFactory.openSession();
        try {
            Employee employee = session.get(Employee.class, empId);
            return employee;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(Integer empId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getEmployeeById(empId));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            if (name == null || name.trim().equals("")) {
                name = "%";
            }else {
                name = "%"+name+"%";
            }
            List list = session.createQuery("from Employee where employeeName like :employeeName")
                    .setParameter("employeeName",name).list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
