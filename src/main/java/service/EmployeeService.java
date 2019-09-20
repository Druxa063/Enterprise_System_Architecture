package service;

import model.Employee;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    void delete(int id);

    void update(Employee employee);

    Employee getById(int id);

    List<Employee> getAll();
}
