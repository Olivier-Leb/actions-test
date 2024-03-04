package example.github.actions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    @Test
    public void testSimple() {
        System.out.println("Hello world!");
    }

    @Test
    public void testAssert() {
        assertTrue(true, "Junit KO");
    }

    @Test
    public void testSelenium() {
        //System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .build();

        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("--headless");

        FirefoxDriver driver = new FirefoxDriver(service, opts);

        driver.get("https://www.google.com/");
    }
}