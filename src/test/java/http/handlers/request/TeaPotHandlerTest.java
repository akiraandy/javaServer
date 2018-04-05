package http.handlers.request;

import http.IO.file.InvalidPathException;
import http.controllers.TeaPotController;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TeaPotHandlerTest {

    TeaPotController handlerUnderTest;

    @BeforeEach
    void setUp() {
        handlerUnderTest = new TeaPotController();
    }

    @Test
    void returns418code() {
        String rawRequest = "GET /coffee HTTP/1.1\n";
        HttpRequest request = new HttpRequest(rawRequest);

        HttpResponse result = handlerUnderTest.generateResponse(request);

        assertEquals(Status.I$m_a_teapot, result.getStatus());

    }

    @Test
    void returns200code() {

        String rawRequest = "GET /tea HTTP/1.1\n";
        HttpRequest request = new HttpRequest(rawRequest);

        HttpResponse result = handlerUnderTest.generateResponse(request);

        assertEquals(Status.OK, result.getStatus());
    }
}