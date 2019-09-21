package service;

import dao.JPAEmployee;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private JPAEmployee jpaDao;

    public void create(Employee employee) {
        jpaDao.create(employee);
    }

    public void delete(int id) {
        jpaDao.delete(id);
    }

    public void update(Employee employee) {
        jpaDao.update(employee);
    }

    public Employee getById(int id) {
        return jpaDao.getById(id);
    }

    public List<Employee> getAll() {
        return jpaDao.getAll();
    }
}
