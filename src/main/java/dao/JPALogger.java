package dao;

import model.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "JPALogger")
public class JPALogger {

    private EntityManager entityManager = EntityManagerFactoryUtils.getInstance().createEntityManager();

    public void create(Logger logger) {
        entityManager.getTransaction().begin();
        entityManager.persist(logger);
        entityManager.getTransaction().commit();
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }

    public void update(Logger logger) {
        entityManager.getTransaction().begin();
        entityManager.merge(logger);
        entityManager.getTransaction().commit();
    }

    public Logger getById(int id) {
        return entityManager.find(Logger.class, id);
    }

    public List<Logger> getAll() {
        System.out.println("JPA ----------- getAll");
        Query query = entityManager.createQuery("SELECT l FROM Logger l");
        return query.getResultList();
    }
}
