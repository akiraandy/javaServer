package http.handlers.request;

import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PutRequestHandlerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException, InvalidRequestException {
        FileIO fileIO = new FileIO("./public");
        HttpRequest httpRequest = putRequest();
        httpRequest.setBody(data());
        httpResponse = new PutRequestHandler(httpRequest, fileIO).returnResponse();
    }


    @AfterEach
    void tearDown() throws IOException {
        byte[] bytes = "".getBytes();
        Path file = Paths.get("./public/form");
        Files.write(file, bytes);
    }

    @Test
    void returnResponse() {
        assertEquals("200", httpResponse.getStatus());
        assertEquals("OK", httpResponse.getReasonPhrase());
    }

    private HttpRequest putRequest() throws InvalidRequestException {
        String rawRequest = "PUT /form HTTP/1.1\\r\\n\" +\n" +
                "Host: www.nowhere123.com\r\\n\\n" +
                "Accept: image/gif, image/jpeg, */*\\r\\n\" +\n" +
                "Accept-Language: en-us\\r\\n\" +\n" +
                "Accept-Encoding: gzip, deflate\\r\\n\" +\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
        return new HttpRequest(rawRequest);
    }

    private byte[] data() {
        return "MyData".getBytes();
    }
}