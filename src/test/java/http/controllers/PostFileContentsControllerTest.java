package http.controllers;

import http.IO.file.FileIO;
import http.IO.file.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PostFileContentsControllerTest {

    public static final String REQUEST_FILE = "POST /testing.txt HTTP/1.1\n";
    public static final String REQUEST_DIRECTORY = "POST /directory HTTP/1.1\n";
    public static final String STATUS_CREATED = "201";
    public static final String STATUS_MESSAGE_CREATED = "Created";
    public static final String STATUS_OK = "200";
    public static final String STATUS_MESSAGE_OK = "OK";

    private HttpResponse httpResponseFile;
    private HttpResponse httpResponseDirectory;

    @BeforeEach
    void setUp() throws IOException {
        IFileIO IFileIO = new FileIO("./public");
        HttpRequest httpRequest = new HttpRequest(REQUEST_FILE);
        HttpRequest httpRequest1 = new HttpRequest(REQUEST_DIRECTORY);
        httpResponseFile = new PostFileContentsController(IFileIO).generateResponse(httpRequest);
        httpResponseDirectory = new PostFileContentsController(IFileIO).generateResponse(httpRequest1);
    }

    @Test
    void generateResponse() {
        assertEquals("/directory/data", httpResponseDirectory.getHeaders().get("Location"));
    }
}