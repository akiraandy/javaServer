package cob_spec.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;

public class TeaPotController implements IController {
    private HttpResponse handleGet(HttpRequest httpRequest) {
        HttpResponse response = new HttpResponse();
        if (httpRequest.path().equals("/coffee")) {
            response.setStatus(Status.Im_a_teapot);
            response.setBody("I'm a teapot");
        } else {
            response.setStatus(Status.OK);
            response.setEmptyBody();
        }
        return response;
    }

    public HttpResponse generateResponse(HttpRequest httpRequest) {
        switch (httpRequest.method()) {
            case GET:
                return handleGet(httpRequest);
            default:
                return new InvalidRequestController().generateResponse(httpRequest);
        }
    }
}
