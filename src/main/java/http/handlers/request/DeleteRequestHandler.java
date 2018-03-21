package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.directory.InvalidResourceHandler;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

import java.io.IOException;

public class DeleteRequestHandler implements IRequestHandler {
    private final HttpRequest httpRequest;
    private final InvalidResourceHandler invalidResourceHandler;
    private final FileHandler fileHandler;

    public DeleteRequestHandler(HttpRequest httpRequest, FileIO fileIO) {
        this.httpRequest = httpRequest;
        this.invalidResourceHandler = new InvalidResourceHandler();
        this.fileHandler = new FileHandler(fileIO);
    }

    @Override
    public HttpResponse returnResponse() throws IOException {
        if (PathChecker.validRoute(httpRequest.path()) && PathChecker.deletePermitted(httpRequest.path()) && deleted(httpRequest.path())) {
            return createResponse();
        } else  {
            return invalidResourceHandler.generateResponse();
        }
    }

    public boolean deleted(String path) throws IOException {
        return fileHandler.deleteFile(path);
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
