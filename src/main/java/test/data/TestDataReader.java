package test.data;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TestDataReader {
    private final static String TEST_DATA_LOCATION = "src/configs/Configuration.properties";

    @SneakyThrows
    public static TestData loadTestData() {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream(TEST_DATA_LOCATION),
                    StandardCharsets.UTF_8));
        } catch (IOException ex) {
        }
        return new TestData(properties);
    }
}
