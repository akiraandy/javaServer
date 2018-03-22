package http.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements IClient {
    ServerSocket serverSocket;
    Socket socket;
    public Client(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        openConnection();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void openConnection() {
        try {
            this.socket = serverSocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
