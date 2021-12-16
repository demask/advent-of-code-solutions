package util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Util {

    public static List<String> adventInput(String adventYear, String adventDay) {
        Properties properties = loadProperties();

        HttpEntity<String> requestEntity = createRequestEntity(properties);

        ResponseEntity<String> responseEntity = getResponse(properties, requestEntity, adventYear, adventDay);

        return convertResponse(responseEntity);
    }

    private static HttpEntity<String> createRequestEntity(Properties properties) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "session=" + properties.getProperty("sessionCookie"));

        return new HttpEntity<String>(null, requestHeaders);
    }

    private static ResponseEntity<String> getResponse(Properties properties, HttpEntity<String> requestEntity, String year, String day) {
        final String url = properties.getProperty("url").replaceFirst("yearValue", year).replaceFirst("dayValue", day);

        return new RestTemplate().exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    private static List<String> convertResponse(ResponseEntity<String> response) {
        return Arrays.asList(Objects.requireNonNull(response.getBody()).split("\n"));
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try {
            properties.load(Util.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return properties;
    }

}
