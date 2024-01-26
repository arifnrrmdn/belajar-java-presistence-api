package arif10522014unikom;

import arif10522014unikom.jpa.entity.Image;
import arif10522014unikom.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LargeObjectTest {

    @Test
    void largeObject() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();



         Image image = new Image();
        image.setName("Contoh Image");
        image.setDescription("Contoh Deskripsi Image");

        // khusus di mac dan linux
        // byte[] bytes = Files.readAllBytes(Path.of(getClass().getResource("/images/sample.png").getPath()));

        // pake ini kalo di windows
        InputStream inputStream = getClass().getResourceAsStream("/images/sample.jpg");
        byte[] bytes = inputStream.readAllBytes();


        image.setImage(bytes);

        entityManager.persist(image);

        entityManager.remove(image);

        entityTransaction.commit();
        entityManager.close();
    }
}
