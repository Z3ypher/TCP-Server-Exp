/*To do list  * means it has been completed
*Create Server Framework 
*Create Client Framework
*Make a UI that allows the user to deisgnate the deisred port.
*If that fails, search for open ports (1024-65335)
Implement Security Encryption Controls
Look into options for converting into a FTP.
The program seems to have a minor delay on when the server and client connect. THe delays goes from 10 to 40 secondes. Afterwards, the connection allows for instant transmission.

*/
import java.io.*;
import java.net.*;
import java.util.Random;

public class Main {
  public static void main(String[] args) throws IOException {
    TCPServer server = new TCPServer();
    TCPClient client = new TCPClient();
    PortGeneratorUI portSelect = new PortGeneratorUI();
    portSelect.main(); // Generates the UI for chosing the port for the sever and client
    int chosen_port = portSelect.getPortNumber();
    if (chosen_port <= 0 || chosen_port > 65335){
      System.out.println("Invalid port number. Moving onto randomly generated port number.");
      int port = generatePort(); // generates a random port between 1024 and 65335
      server.setPort(port); // Server port generated
      client.setPort(port); // client port generated
      return;
    }
    else {
      server.setPort(chosen_port); // Server port set
      client.setPort(chosen_port); // Client port set
    }
    
    
   server.main();
   client.main();
  }

  public static int generatePort() {
    int min = 1024;
    int max = 65335;
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
  }

  
}