package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsControllerTest {

    public static final String REQUEST = "GET /invalid HTTP/1.1\n";
    public static final String STATUS  = "405";
    public static final String STATUS_MESSAGE = "Method Not Allowed";
    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest(REQUEST);
        httpResponse = new OptionsController().generateResponse(httpRequest);
    }

    @Test
    void generateResponse() {

    }
}