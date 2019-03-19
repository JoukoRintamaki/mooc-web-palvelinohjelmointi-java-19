
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws IOException {
        int i = 0;
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
                        printWriter.println("HTTP/1.1 302 Found");
                        printWriter.println("Location: http://localhost:8080");
                        i++;
                        System.out.println(i);
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
