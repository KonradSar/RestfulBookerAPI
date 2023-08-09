package test.data.model;

import java.util.Properties;

public class Url {
    protected final String baseUrl;

    public Url(Properties properties) {
        this.baseUrl = properties.getProperty("url.baseURL");
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
