package http.handlers.redirect;

import http.controllers.RedirectController;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class RedirectControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        httpResponse = new RedirectController().generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /redirect HTTP/1.0\n";
    }

    @Test
    void generateResponse() {
        assertEquals(Status.Found, httpResponse.getStatus());
        assertTrue(httpResponse.getHeaders().containsKey("Location"));
        assertTrue(httpResponse.getHeaders().containsValue("/"));
    }
}