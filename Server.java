
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    // PORT var
    // CopyOnWriteArrayList allows multiple clients to be handled appropriately
    static final int PORT = 9000;
    static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serer is up and running.");

            // Accept incoming messages
            while (true) { 
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client has connected: " + clientSocket);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static class ClientHandler implements Runnable {

        @Override
        public void run() {
            throw new UnsupportedOperationException("Unimplemented method 'run'");
        }

    }
}
