package ra.demo_thymeleaf.dao;

import ra.demo_thymeleaf.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> getEmployees();
    Employee getEmployeeById(Integer empId);
    public boolean addEmployee(Employee employee);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(Integer empId);
    List<Employee> getEmployeeByName(String name);
}
