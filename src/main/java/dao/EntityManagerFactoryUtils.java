package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtils {

    private static EntityManagerFactory ourInstance;

    private EntityManagerFactoryUtils() {
    }

    public static EntityManagerFactory getInstance() {
        if (ourInstance == null) {
            return Persistence.createEntityManagerFactory("lab_esa");
        }
        return ourInstance;
    }
}
