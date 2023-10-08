import io.restassured.RestAssured;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestApi {

    @Test
    @DisplayName("Task 1 RestAssured")
    public void testTask1RA() {
        String URL = "https://restful-booker.herokuapp.com/booking";

        RestAssured.
                when()
                .get(URL)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Task 1 Apache")
    public void testTask1A() throws IOException {
        String URL = "https://restful-booker.herokuapp.com/booking";

        HttpUriRequest request = new HttpGet(URL);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assertions.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Task 2 RestAssured")
    public void testTask2RA() {
        String URL = "https://restful-booker.herokuapp.com/booking";

        String responseBody = RestAssured
                .when()
                .get(URL)
                .then()
                .extract()
                .asString();
        Assertions.assertTrue(responseBody.contains("{\"bookingid\":9}"));
    }

    @Test
    @DisplayName("Task 2 Apache")
    public void testTask2A() throws IOException {

        String URL = "https://restful-booker.herokuapp.com/booking";

        HttpUriRequest request = new HttpGet(URL);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        String responseBody = EntityUtils.toString(response.getEntity());
        Assertions.assertTrue(responseBody.contains("{\"bookingid\":9}"));
    }
}
