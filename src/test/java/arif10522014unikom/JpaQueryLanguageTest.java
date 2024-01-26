package arif10522014unikom;

import arif10522014unikom.jpa.entity.Brand;
import arif10522014unikom.jpa.entity.Member;
import arif10522014unikom.jpa.entity.Product;
import arif10522014unikom.jpa.entity.SimpleBrand;
import arif10522014unikom.jpa.util.JpaUtil;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void select(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void whereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where " +
                "m.name.firstName = :firstName and m.name.lastName = :lastName", Member.class);

        query.setParameter("firstName", "Arif");
        query.setParameter("lastName", "Ramadhan");

        List<Member> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member member : resultList) {
                System.out.println(member.getId() + " : " + member.getFullName());
            }


            entityTransaction.commit();
            entityManager.close();
        }
    }

    @Test
    void whereClausePakEko() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where " +
                "m.name.firstName = :firstName and m.name.lastName = :lastName", Member.class);
        query.setParameter("firstName", "Eko");
        query.setParameter("lastName", "Khannedy");

        List<Member> members = query.getResultList();
        for (Member member : members) {
            System.out.println(member.getId() + " : " + member.getFullName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand","Samsung");

        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName() + " : " + product.getBrand().getName());
        }

        entityTransaction.commit();
        entityManager.close();
        }

    @Test
    void joinFetchClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join fetch p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand","Samsung");

        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName() + " : " + product.getBrand().getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void orderByClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands){
            System.out.println(brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void insertRandomBrand() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (int i = 0; i < 100; i++){
            Brand brand = new Brand();
            brand.setId(String.valueOf(i));
            brand.setName("Brand " + i);
            entityManager.persist(brand);
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void limitOffest() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);

        query.setFirstResult(10);
        query.setMaxResults(10);

        List<Brand> resultList = query.getResultList();
        for (Brand brand : resultList){
            System.out.println(brand.getId());
        }


        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void namedQuery() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> namedQuery = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        namedQuery.setParameter("name", "Apple");

        List<Brand> resultList = namedQuery.getResultList();
        for (Brand brand : resultList){
            System.out.println(brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void selectSomeField() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name = :name", Object[].class);
        query.setParameter("name", "Apple");

        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects){
            System.out.println(object[0] + " : " + object[1]);
        }


        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void selectNewConstructor() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new arif10522014unikom.jpa.entity.SimpleBrand(b.id,b.name) from Brand b where b.name = :name", SimpleBrand.class);
        query.setParameter("name", "Apple");
        List<SimpleBrand> resultList = query.getResultList();
        for (SimpleBrand simpleBrand : resultList){
            System.out.println(simpleBrand.getId() + " : " + simpleBrand.getName());
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void aggregateQuery() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select min(p.price), max(p.price), avg(p.price) from Product p", Object[].class);
        Object[] singleResult = query.getSingleResult();

        System.out.println("Min : " + singleResult[0]);
        System.out.println("Max : " + singleResult[1]);
        System.out.println("Avg : " + singleResult[2]);


        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void agregateQueryGroupBy() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b group by b.id having min(p.price) > :min", Object[].class);
        query.setParameter("min",500_000L);

        List<Object[]> resultList = query.getResultList();
        for (Object[] object : resultList){
            System.out.println("Brand : " + object[0]);
            System.out.println("Min : " + object[1]);
            System.out.println("Max : " + object[2]);
            System.out.println("Average : " + object[3]);
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void nativeQuery() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query nativeQuery = entityManager.createNativeQuery("select * from brands where brands.created_at is not null", Brand.class);
        List<Brand> resultList = nativeQuery.getResultList();

        for (Brand brand : resultList){
            System.out.println(brand.getId() + " : " + brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void namedNativeQuery() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> namedQuery = entityManager.createNamedQuery("Brand.native.findAll", Brand.class);
        List<Brand> resultList = namedQuery.getResultList();

        for (Brand brand : resultList){
            System.out.println(brand.getId() + " : " + brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void nonQuery() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createQuery("update Brand b set b.name = :name where b.id = :id");
        query.setParameter("name","Samsung Updated");
        query.setParameter("id","samsung");
        int impactadRecords = query.executeUpdate();
        System.out.println("Success update " + impactadRecords + " records");


        entityTransaction.commit();
        entityManager.close();

    }
}


