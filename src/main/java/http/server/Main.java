package http.server;


import http.request.error.InvalidRequestException;
import http.utils.MainArgumentParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidRequestException {
        MainArgumentParser argumentParser = new MainArgumentParser(args);
        new Server(argumentParser.getPortNumber(), argumentParser.getWorkingDirectory());
    }
}
