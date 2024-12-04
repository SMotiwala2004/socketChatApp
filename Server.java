
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String Username;

        // Client Handler constructer
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                
            } catch (Exception e) {
            }
        }

        private String getUsername() throws IOException {
            out.println("What is your username?");
            return in.readLine();
        }

        public void sendMessage(String message) {
            out.println(message);
            out.println("What is your message?");
        }
    }
}
