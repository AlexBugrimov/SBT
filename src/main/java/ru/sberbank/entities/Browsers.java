package ru.sberbank.entities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum Browsers {
    IE("webdriver.ie.driver", "IEDriverServer.exe") {
        @Override
        public WebDriver get() {
            return new InternetExplorerDriver();
        }
    },

    FIREFOX("webdriver.gecko.driver", "geckodriver.exe") {
        @Override
        public WebDriver get() {
            return new FirefoxDriver();
        }
    },

    CHROME("webdriver.chrome.drive", "chromedriver.exe") {
        @Override
        public WebDriver get() {
            return new ChromeDriver();
        }
    };

    private final String key;
    private final String value;

    Browsers(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public abstract WebDriver get();

    public String key() {
        return this.key;
    }

    public String value() {
        return this.value;
    }
}
