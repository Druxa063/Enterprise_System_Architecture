package service;

import dao.JPAEmployee;
import model.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    @EJB(beanName = "JPAEmployeeImpl")
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
