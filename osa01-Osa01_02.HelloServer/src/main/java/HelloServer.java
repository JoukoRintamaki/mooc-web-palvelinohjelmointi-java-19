
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws IOException {
        String response = new String(readAllBytes(get("index.html")));
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    Scanner scanner = new Scanner(socket.getInputStream());
                    if ("GET /quit HTTP/1.1".equals(scanner.nextLine())) {
                        scanner.close();
                        socket.close();
                        break;
                    } else {
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);                                                
                        printWriter.println("HTTP/1.1 200 OK");
                        printWriter.println("");
                        printWriter.println(response);                        
                        scanner.close();
                        socket.close();
                        printWriter.close();
                    }
                }
            }
            serverSocket.close();
        }
    }
}
