import ie.jak.Config;
import ie.jak.entites.Salon;
import ie.jak.service.exceptions.SalonAlreadyExists;
import ie.jak.service.exceptions.SalonMalformedException;
import ie.jak.service.exceptions.SalonNotFoundException;
import ie.jak.service.SalonService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
public class ServiceTests {

    @Autowired
    SalonService salonService;

    @Test
    @Order(1)
    public void testSalonCount() {
        Assertions.assertEquals(3, salonService.count());
    }

    @Test
    @Order(2)
    public void testSalonFindAll() {
        Assertions.assertEquals(3, salonService.findAll().size());
    }

    @Test
    @Order(3)
    @SneakyThrows
    public void testSalonFindByIdFound() {
        Salon salon = salonService.findById(2);
        Assertions.assertNotNull(salon);
        Assertions.assertEquals("Salon Jen", salon.getSalonName());
    }

    @Test
    @Order(4)
    public void testCartoonFindByIdNotFound() {
        Assertions.assertThrows(SalonNotFoundException.class, ()->{salonService.findById(22222);});
    }

    @Test
    @Order(5)
    public void testDeleteByIdNotFound() {
        Assertions.assertThrows(SalonNotFoundException.class, ()->{salonService.deleteById(2222222);});
    }

    @Test
    @Order(6)
    public void testDeleteById(){
        Assertions.assertNotNull(salonService.findById(2));
        salonService.deleteById(2);
        Assertions.assertThrows(SalonNotFoundException.class, ()->{salonService.findById(2);});
    }

    @Test
    @Order(7)
    @SneakyThrows
    public void testNewSalonOk(){
        int oldCount = salonService.count();
        Salon salon = new Salon(4,"Salon Owen", "X12Y345", 0831122334F, 1111110);
        Salon newSalon = salonService.add(salon);
        Assertions.assertEquals(oldCount+1, salonService.count());
        Assertions.assertNotNull(newSalon);
        Assertions.assertEquals("Salon Owen", newSalon.getSalonName());
    }

    @Test
    @Order(8)
    @SneakyThrows
    public void testNewSalonBadName(){
        Salon salon = new Salon(4,"", "X12Y345", 0831122334F, 1111110);
        Assertions.assertThrows(SalonMalformedException.class, ()->{salonService.add(salon);});
    }

    @Test
    @Order(9)
    @SneakyThrows
    public void testNewSalonBadID(){
        Salon salon = new Salon(-1,"Salon Owen", "X12Y345", 0831122334F, 1111110);
        Assertions.assertThrows(SalonMalformedException.class, ()->{salonService.add(salon);});
    }

    @Test
    @Order(10)
    @SneakyThrows
    public void testSaveClashPK(){
        Salon salon = new Salon(3,"Salon Owen", "X12Y345", 0831122334F, 1111110);
        Assertions.assertThrows(SalonAlreadyExists.class, ()->{salonService.add(salon);});
    }

    @Test
    @Order(11)
    @SneakyThrows
    public void testEditSalonIdNotExists(){
        Assertions.assertThrows(SalonNotFoundException.class, ()->{salonService.changeName("New Name", 88);});

    }

    @Test
    @Order(12)
    @SneakyThrows
    public void testEditSalonBadName(){
        Assertions.assertThrows(SalonMalformedException.class, ()->{salonService.changeName("", 2);});
    }

    @Test
    @Order(13)
    @SneakyThrows
    public void testEditSalonAllOk(){
        Assertions.assertTrue(salonService.changeName("New Name", 2));
    }
}
