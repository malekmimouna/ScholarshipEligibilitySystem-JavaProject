import java.io.*;
import java.net.*;

public class EligibilityServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Server started...");

            while (true) {
                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                String studentData = in.readLine(); // e.g. "John,3.5,2000"
                String[] parts = studentData.split(",");
                double gpa = Double.parseDouble(parts[1]);
                double income = Double.parseDouble(parts[2]);

                // Simulate logic (replace with real call to EligibilityChecker later)
                boolean eligible = gpa >= 3.0 && income <= 3000;

                out.println(eligible ? "Eligible" : "Not Eligible");
                client.close();
            }
        } catch (NumberFormatException e) {
           
            e.printStackTrace();
        }
    }
}
