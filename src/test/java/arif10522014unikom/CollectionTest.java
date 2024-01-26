package arif10522014unikom;

import arif10522014unikom.jpa.entity.Customer;
import arif10522014unikom.jpa.entity.Member;
import arif10522014unikom.jpa.entity.Name;
import arif10522014unikom.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectionTest {

    @Test
    void create() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setFirstName("Arif");
        name.setMiddleName("N");
        name.setLastName("Ramadhan");

        Member member = new Member();
        member.setName(name);
        member.setEmail("arif123@example.com");

        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Futsal");
        member.getHobbies().add("Martial");

        entityManager.persist(member);


        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void update() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.getHobbies().add("Swiming");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void updateSkills() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 100);
        member.getSkills().put("PHP",85);
        member.getSkills().put("Javascript",90);

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();

    }



}
