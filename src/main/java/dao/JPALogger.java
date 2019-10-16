package dao;

import model.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "JPALogger")
public class JPALogger {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("lab_esa").createEntityManager();

    public void create(Logger logger) {
        entityManager.merge(logger);
    }

    public void delete(int id) {
        entityManager.remove(getById(id));
    }

    public void update(Logger logger) {
        entityManager.merge(logger);
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
