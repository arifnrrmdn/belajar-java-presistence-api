package arif10522014unikom;

import arif10522014unikom.jpa.entity.*;
import arif10522014unikom.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmbeddedTest {

    @Test
    void column() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Arif");
        name.setMiddleName("N");
        name.setLastName("Ramadhan");

        Member member = new Member();
        member.setEmail("arif123@example.com");
        member.setName(name);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void embeddedId() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartementId id = new DepartementId();
        id.setCompanyId("anr");
        id.setDepartementId("tech");

        Departement departement = new Departement();
        departement.setId(id);
        departement.setName("Technology");

        entityManager.persist(departement);

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void embeddedIdFind() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartementId id = new DepartementId();
        id.setCompanyId("anr");
        id.setDepartementId("tech");

        Departement departement = entityManager.find(Departement.class, id);
        Assertions.assertEquals("Technology", departement.getName());

        entityTransaction.commit();
        entityManager.close();

    }

}
