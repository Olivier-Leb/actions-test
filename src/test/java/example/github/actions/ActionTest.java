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

    @Test
    public void testSecret() {
        assertFalse(PropertyLoader.getConfigValue("secret1").isEmpty(), "Empty secret");
        assertEquals(PropertyLoader.getConfigValue("secret1"), "12345", "Wrong secret");

        assertFalse(PropertyLoader.getConfigValue("secret2").isEmpty(), "Empty secret");
        assertEquals(PropertyLoader.getConfigValue("secret2"), "$eCr3T.poW4fv$ed@n6", "Wrong secret");
    }

    @Test
    public void testEnvVariable() {
        assertFalse(System.getenv("ENV_SECRET1").isEmpty(), "Empty secret");
        assertEquals(System.getenv("ENV_SECRET1"), "12345", "Wrong secret");

        assertFalse(System.getenv("ENV_SECRET2").isEmpty(), "Empty secret");
        assertEquals(System.getenv("ENV_SECRET2"), "$eCr3T.poW4fv$ed@n6", "Wrong secret");
    }
}