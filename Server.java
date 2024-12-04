
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

                // Create a new Client Handler for the Client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void broadcast(String message, ClientHandler sender) {
        for(ClientHandler client : clients) {
            client.sendMessage(message);
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
                Username = getUsername();
                System.out.println("User [" + Username +"] has connected!");
                out.println("Welcome to the chat " + Username + "!");
                out.println("Type your message below");
                String inputLine;
               
                while((inputLine = in.readLine()) != null) {
                    System.out.println("[" + Username + "]: " + inputLine );
                    
                    broadcast("[" + Username + "]: " + inputLine, this );
                }
                // Remove client handler from list

                clients.remove(this);
                //Close I/O and Socket
                in.close();
                out.close();
                clientSocket.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Get Username Method
        private String getUsername() throws IOException {
            out.println("What is your username?");
            return in.readLine();
        }
        // Send Message method
        public void sendMessage(String message) {
            out.println(message);
            out.println("What is your message?");
        }
    }
}
