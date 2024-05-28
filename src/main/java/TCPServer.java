import java.io.*;
import java.net.*;

public class TCPServer {
    private int port;
    
    public void main() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Create a new thread to handle the client connection
                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    // Setting the port number that was made in Main
    public void setPort(int x) {
        this.port = x;
        System.out.println("Server Port set to " + port);
    }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String text;
            while ((text = reader.readLine()) != null) {
                System.out.println("Received message from client: " + text);
                writer.println("Echo: " + text); // Echo the message back to the client
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}