import java.io.*;
import java.net.*;

public class TCPClient {
    private int port;
    
    public void main() {
        String hostname = "localhost";

        try (Socket socket = new Socket(hostname, port);
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true);
             InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            // Send a message to the server
            writer.println("Hello, server!");

            // Read the server's response
            String response = reader.readLine();
            System.out.println("Server response: " + response);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    // Setting the port number that was set in Main
    public void setPort(int x) {
        this.port = x;
        //System.out.println("Client Port set to " + port);
    }
}