package example.github.actions;

import example.github.actions.utils.PropertyLoader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    @Test
    public void testAssert() {
        assertTrue(true, "Junit KO");
    }

    @Test
    public void testProperties() {
        assertEquals(
                "firefox",
                PropertyLoader.getConfigValue("driver.default"),
                "Wrong value for property 'driver.default'"
        );
        assertEquals(
                "https://www.google.com",
                PropertyLoader.getConfigValue("url.base"),
                "Wrong value for property 'url.base'"
        );
    }

    @Test
    public void testFirefoxSelenium() {
        if (!PropertyLoader.getConfigValue("driver.firefox").isEmpty()) {
            System.setProperty("webdriver.gecko.driver", PropertyLoader.getConfigValue("driver.firefox"));
        }

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .build();

        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("--headless");

        FirefoxDriver driver = new FirefoxDriver(service, opts);

        driver.get(PropertyLoader.getConfigValue("url.base"));

        assertEquals(
                PropertyLoader.getConfigValue("url.base") + "/",
                driver.getCurrentUrl(),
                "Wrong page opened"
        );
    }
}