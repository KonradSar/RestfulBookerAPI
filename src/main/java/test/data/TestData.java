package test.data;

import test.data.model.Url;

import java.util.Properties;

public class TestData {
    private Url url;

    public TestData(Properties properties) {
        this.url = (new Url(properties));
    }

    public Url getUrl() {
        return url;
    }
}
