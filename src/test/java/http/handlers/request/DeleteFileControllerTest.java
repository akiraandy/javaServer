package http.handlers.request;

import http.IO.file.FileIO;
import http.IO.file.IFileIO;
import http.controllers.DeleteFileController;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.router.Router;
import http.status.InvalidStatusCodeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFileControllerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException, InvalidRequestException, InvalidStatusCodeException {
        IFileIO IFileIO = new FileIO("./public");
        Router router = new Router();
        router.addRoute("/form", httpMethod.DELETE, new DeleteFileController(router, IFileIO));
        httpResponse = new DeleteFileController(router, IFileIO).generateResponse(deleteRequest());
    }

    @Test
    void returnResponse() {
        assertEquals("200", httpResponse.getStatus());
        assertEquals("OK", httpResponse.getReasonPhrase());
    }

    @AfterEach
    void tearDown() throws IOException {
        Path path = Paths.get("./public/form");
        Files.write(path, "".getBytes());
    }

    private HttpRequest deleteRequest() throws InvalidRequestException, UnsupportedEncodingException {
        String rawRequest = "DELETE /form HTTP/1.1\\r\\n\" +\n" +
                "Host: www.nowhere123.com\\r\\n\" +\n" +
                "Accept: image/gif, image/jpeg, */*\\r\\n\" +\n" +
                "Accept-Language: en-us\\r\\n\" +\n" +
                "Accept-Encoding: gzip, deflate\\r\\n\" +\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
        return new HttpRequest(rawRequest);
    }
}