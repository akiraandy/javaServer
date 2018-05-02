package cob_spec;

import cob_spec.controllers.DeleteFileController;
import http.IO.FileIO;
import http.IO.IFileIO;
import http.method.HttpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Router;
import http.status.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteFileControllerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException {
        IFileIO IFileIO = new FileIO("./public");
        Router router = new Router();
        router.addRoute("/form", HttpMethod.DELETE, new DeleteFileController(router, IFileIO));
        httpResponse = new DeleteFileController(router, IFileIO).generateResponse(deleteRequest());
    }

    @Test
    void responseReturnsA200Status() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @AfterEach
    void tearDown() throws IOException {
        Path path = Paths.get("./public/form");
        Files.write(path, "".getBytes());
    }

    private HttpRequest deleteRequest() {
        return new HttpRequest("DELETE /form HTTP/1.1\r\n");
    }
}