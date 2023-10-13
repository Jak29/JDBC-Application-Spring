package ie.jak;

import ie.jak.dao.SalonDao;
import ie.jak.dao.SalonDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class MainApp {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(applicationContext.getMessage("welcome", null, Locale.getDefault()));
        SalonDao salonDao = applicationContext.getBean(SalonDaoImpl.class);
        salonDao.findAll().forEach(System.out::println);
    }
}
