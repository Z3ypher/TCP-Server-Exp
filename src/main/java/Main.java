/*To do list  * means it has been completed
*Create Server Framework 
*Create Client Framework
*Make a UI that allows the user to deisgnate the deisred port.
*If that fails, search for open ports (1024-65335)
Implement Security Encryption Controls
Look into options for converting into a FTP.

*/
import java.io.*;
import java.net.*;
import java.util.Random;

public class Main {
  public static void main(String[] args) throws IOException {
    TCPServer server = new TCPServer();
    TCPClient client = new TCPClient();
    PortGeneratorUI portSelect = new PortGeneratorUI();
    portSelect.main();
    int chosen_port = portSelect.getPortNumber();
    if (chosen_port <= 0 || chosen_port > 65335){
      System.out.println("Invalid port number. Moving onto randomly generated port number.");
      int port = generatePort(); // successfully generate a random port between 1024 and 65335
      server.setPort(port); // Server port set
      client.setPort(port); // client port set
      return;
    }
    else {
      System.out.println(chosen_port + " is called from main");
    }
    
    
   //server.main();
    //client.main();
  }

  public static int generatePort() {
    // Define the range
    int min = 1024;
    int max = 65335;

    // Create an instance of Random class
    Random random = new Random();

    // Generate a random number within the range
    return random.nextInt(max - min + 1) + min;
  }

  
}