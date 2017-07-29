package ru.sberbank.entities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Settings {
    private final Properties properties;

    Settings() {
        this.properties = new Properties();
    }

    void load(final InputStream stream) {
        try {
            this.properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String value(final String key) {
        return this.properties.getProperty(key);
    }
}
