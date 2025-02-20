package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopApplicationTests {

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
    }
}