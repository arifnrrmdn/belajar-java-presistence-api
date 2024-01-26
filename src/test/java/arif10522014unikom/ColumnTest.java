package arif10522014unikom;

import arif10522014unikom.jpa.entity.Customer;
import arif10522014unikom.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    void column() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Arif N Ramadhan");
        customer.setPrimaryEmail("arif123@example.com");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();

    }


}
