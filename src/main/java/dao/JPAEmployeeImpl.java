package dao;


import model.Employee;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "JPAEmployeeImpl")
public class JPAEmployeeImpl implements JPAEmployee {

    private EntityManager entityManager = EntityManagerFactoryUtils.getInstance().createEntityManager();

    @Override
    public void create(Employee employee) {
        System.out.println("Crete Employee");
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
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
