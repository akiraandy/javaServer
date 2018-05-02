package http.connectionProcess;

public class ConnectionProcessMultiThread {

    public void execute(HttpConnectionToProcess httpConnectionToProcess) {
        HttpConnectionToProcessThread httpConnectionToProcessThread = new HttpConnectionToProcessThread(httpConnectionToProcess);
        Thread thread;
        thread = new Thread(httpConnectionToProcessThread);
        thread.start();
    }
}
