/*To do list  * means it has been completed
*Create Server Framework 
*Create Client Framework
Make a UI that allows the user to deisgnate the deisred port.
If that fails, search for open ports (1024-65335)
Implement Security Encryption Controls
Look into options for converting into a FTP.

*/
import java.io.*;
import java.net.*;

public class Main {
  public static void main(String[] args) throws IOException {
    TCPServer server = new TCPServer();
    TCPClient client = new TCPClient();
    server.main();
    client.main();
  }
}