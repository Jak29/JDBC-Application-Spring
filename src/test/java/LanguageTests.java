import ie.jak.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@ActiveProfiles({"test"})
public class LanguageTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testFrench(){
        String message = applicationContext.getMessage("welcome", null, Locale.FRENCH);
        Assertions.assertEquals("Bienvenue à SOFT8020", message);
    }

    @Test
    public void testMessageBean(){
        Assertions.assertNotNull(applicationContext.getBean(MessageSource.class));
    }
}
