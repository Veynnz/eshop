package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EshopApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // This method is intentionally empty.
        // Its purpose is to check if Spring loads.
        // This test makes sure the app starts.
        // It passes if the app starts without any problem.
    }

    @Test
    void mainMethodRunsWithoutError() {
        EshopApplication.main(new String[] {});
        // Check if Application context is not null after main method is called.
        assertNotNull(context);
    }
}