/* Protoype
import java.io.*;
import java.net.*;

public class TCPServer {
    public void build() throws IOException {
        int portNumber = 9090;
        ServerSocket serverSocket = new ServerSocket(portNumber); {
            System.out.println("Server is running and listening on port " + portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle client connection in a separate thread or process
                // (not shown in this example for brevity)
            }
        }
    }
}*/

import java.io.*;
import java.net.*;

public class TCPServer {
    public void main() {
        int port = 9090;
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