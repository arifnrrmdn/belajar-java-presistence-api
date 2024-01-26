package arif10522014unikom;

import arif10522014unikom.jpa.entity.Brand;
import arif10522014unikom.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class ManagedEntityTest {

    @Test
    void managedEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // unmanaged entity
        Brand brand = new Brand();
        brand.setId("xiaomi");
        brand.setName("Xiaomi");

        //managed entity
        entityManager.persist(brand);

        brand.setName("Xiaomay");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void managedEntityFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "apple");
        brand.setName("apple beacukai");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void managedEntityDetach() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "xiaomi");
        entityManager.detach(brand);
        brand.setName("Batagor");

        entityTransaction.commit();
        entityManager.close();
    }
}
