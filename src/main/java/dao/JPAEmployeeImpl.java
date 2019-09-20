package dao;


import model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "JPAEmployeeImpl")
public class JPAEmployeeImpl implements JPAEmployee {

    EntityManager entityManager = Persistence.createEntityManagerFactory("lab_esa").createEntityManager();

    @Override
    public void create(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getById(id));
    }

    @Override
    public void update(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public Employee getById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> getAll() {
        System.out.println("JPA ----------- getAll");
        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
}
