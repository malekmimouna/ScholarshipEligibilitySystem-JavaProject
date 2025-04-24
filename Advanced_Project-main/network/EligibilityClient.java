import java.io.*;
import java.net.*;

public class EligibilityClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String student = "Malek,3.7,2500"; // Example
        out.println(student);

        String response = in.readLine();
        System.out.println("Server says: " + response);

        socket.close();
    }
}
