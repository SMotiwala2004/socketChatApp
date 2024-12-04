import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException{
        try {
            // Create Socket
            Socket s = new Socket("localhost", 9000);
            System.out.println("Connected to the chat server!");

            // Setup input and output readers
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));            

            // Start Thread to handle incoming messages
            new Thread(() -> {
                try {
                    String serverResponse;
                    while((serverResponse = in.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Read Messages from console to the server
            Scanner sc = new Scanner(System.in);
            String userInput;
            while(true) {
                userInput = sc.nextLine();
                out.println(userInput);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
