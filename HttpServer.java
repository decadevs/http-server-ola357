import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        // create a socket
        int port = 1989;
       final ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server is running on port : " + port);
    
        // repeatedly wait for connections, and process
        while (true) {
            // wait for client's request
            Socket clientSocket = serverSocket.accept();
            System.err.println("New client connection");
    
            // open a stream
    
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

    // every time a data is read on the network it is sent back to the
    // The writing flow.
    // The read data is therefore returned to exactly the same client.
    String s;
    while ((s = in.readLine()) != null) {
        System.out.println(s);
        if (s.isEmpty()) {
            break;
        }
    }

    out.write("HTTP/1.0 200 OK\r\n");
    out.write("Date: Fri, 29 Jan 2020 23:59:59 GMT\r\n");
    out.write("Server: Apache/0.8.4\r\n");
    out.write("Content-Type: text/html\r\n");
    out.write("Content-Length: 59\r\n");
    out.write("Expires: Sat, 01 Feb 2020 00:59:59 GMT\r\n");
    out.write("\r\n");
    out.write("<title>Example</title>");
    out.write("<P>http server.</P>");

    // close the stream
    System.err.println("Connection has been terminated");
    out.close();
    in.close();
    clientSocket.close();
    serverSocket.close();
}
}
}