package http.handlers.request;

import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HeadRequestHandlerTest {

    private HttpResponse httpResponse, httpResponse2;

    @BeforeEach
    void setUp() throws IOException, InvalidRequestException {
        FileIO fileIO = new FileIO("./public");
        httpResponse = new HeadRequestHandler(fileIO).returnResponse(httpRequest());
        httpResponse2 = new HeadRequestHandler(fileIO).returnResponse(invalidHttpRequest());
    }

    private HttpRequest httpRequest() throws InvalidRequestException {
        String rawRequest = "HEAD / HTTP/1.1\\r\\n\" +\n" +
                "Host: www.nowhere123.com\\r\\n\" +\n" +
                "Accept: image/gif, image/jpeg, */*\\r\\n\" +\n" +
                "Accept-Language: en-us\\r\\n\" +\n" +
                "Accept-Encoding: gzip, deflate\\r\\n\" +\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
        return new HttpRequest(rawRequest);
    }

    private HttpRequest invalidHttpRequest() throws InvalidRequestException {
            String rawRequest = "HEAD /nowhere HTTP/1.1\\r\\n\" +\n" +
                    "Host: www.nowhere123.com\\r\\n\" +\n" +
                    "Accept: image/gif, image/jpeg, */*\\r\\n\" +\n" +
                    "Accept-Language: en-us\\r\\n\" +\n" +
                    "Accept-Encoding: gzip, deflate\\r\\n\" +\n" +
                    "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
            return new HttpRequest(rawRequest);
    }

    @Test
    void returnResponse() {
        assertEquals("200", httpResponse.getStatus());
        assertEquals("OK", httpResponse.getReasonPhrase());
        assertEquals("404", httpResponse2.getStatus());
        assertEquals("Not Found", httpResponse2.getReasonPhrase());
    }
}