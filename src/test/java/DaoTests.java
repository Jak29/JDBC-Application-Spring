import ie.jak.Config;
import ie.jak.dao.SalonDao;
import ie.jak.entites.Salon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
public class DaoTests {

    @Autowired
    SalonDao salonDao;

    @Test
    @Order(1)
    public void testSaloonCount(){
        Assertions.assertEquals(3, salonDao.count());
    }

    @Test
    @Order(2)
    public void testSalonFindAll(){
        Assertions.assertEquals(3, salonDao.findAll().size());
    }

    @Test
    @Order(3)
    public void testSalonFindById(){
        Optional<Salon> salon = salonDao.findById(1);
        Assertions.assertTrue(salon.isPresent());
        Assertions.assertEquals("Salon Jak", salon.get().getSalonName());
    }

    @Test
    @Order(4)
    public void testCartoonFindByIdNotFound(){
        Optional<Salon> salonOptional = salonDao.findById(1111);
        Assertions.assertFalse(salonOptional.isPresent());
    }

    @Test
    @Order(5)
    public void testSave(){
        int oldCount = salonDao.count();
        Salon salon = new Salon(4,"Salon Owen", "X12Y345", 0831122334F, 1111110);
        salonDao.save(salon);
        Assertions.assertEquals(oldCount+1, salonDao.count());
        Assertions.assertTrue(salonDao.findById(4).isPresent());
        Assertions.assertEquals("Salon Owen", salonDao.findById(4).get().getSalonName());
    }

    @Test
    @Order(6)
    public void testSaveClashPK(){
        Salon salon = new Salon(1,"Salon Owen", "X12Y345", 0831122334F, 1111110);
        Assertions.assertThrows(DuplicateKeyException.class, ()->{salonDao.save(salon);});
    }

    @Test
    @Order(7)
    public void testChangeName(){
        Assertions.assertTrue(salonDao.editName("New Name", 2));
        Assertions.assertEquals("New Name", salonDao.findById(2).get().getSalonName());
    }

//    @Test
//    @Order(8)
//    public void testFindOldest(){
//
//    }

//    @Test
//    @Order(5)
//    public void testdeleteById(){
//        Assertions.assertTrue(salonDao.deleteById(1));
//    }
//
//    @Test
//    @Order(6)
//    public void testdeleteByIdNotFound(){
//        Assertions.assertFalse(salonDao.deleteById(11111));
//    }
}
