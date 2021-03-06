package http.response;

import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpResponseWriterTest {

    public static final String RESPONSE = "HTTP/1.1 " + Status.OK.getCode() + " " + Status.OK + "\r\n" + "Content-Type: html/text" + "\r\n\r\n" + "Test";
    private byte[] byteArray;
    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        httpResponse = createHttpResponse();
        byteArray = new HttpResponseWriter().sendHttpResponse(httpResponse);
    }

    private HttpResponse createHttpResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.OK);
        httpResponse.addHeader("Content-Type", "html/text");
        httpResponse.addToBody("Test");
        return httpResponse;
    }

    @Test
    void responseWriterWritesBodyCorrectly() {
        assertTrue(Arrays.equals(byteArray, RESPONSE.getBytes()));
    }
}