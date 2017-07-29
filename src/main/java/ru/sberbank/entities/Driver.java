package ru.sberbank.entities;

import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Driver {
    private final Settings settings;
    private final WebDriver driver;

    public Driver() throws IOException {
        settings = new Settings();
        driver = loadDriver();
    }

    public WebDriver get() {
        return this.driver;
    }

    public void openSite(final String site) {
        driver.get(site);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void closeBrowser() {
        driver.quit();
    }

    private WebDriver loadDriver() throws IOException {
        ClassLoader loader = getClass().getClassLoader();
        try (InputStream stream = loader.getResourceAsStream("app.properties")) {
            settings.load(stream);
        }

        for (Browsers browser : Browsers.values()) {
            if (browser.name().equalsIgnoreCase(settings.value("browser"))) {
                System.setProperty(browser.key(), String.valueOf(Paths.get("drivers/" + browser.value()).toAbsolutePath()));
                return browser.get();
            }
        }
        return null;
    }
}
