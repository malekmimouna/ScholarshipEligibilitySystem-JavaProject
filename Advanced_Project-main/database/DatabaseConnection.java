package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class  DatabaseConnection {

    
    private static final String URL = "jdbc:mariadb://localhost:3306/scholarship_db"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Malek456789";

    
    private Connection connection;

    
    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    
    public ResultSet getStudents() {
        String query = "SELECT * FROM students";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
        return null;
    }

    public ResultSet getScholarships() {
        String query = "SELECT * FROM scholarships";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error fetching scholarships: " + e.getMessage());
        }
        return null;
    }

    
    public void insertStudent(String name, int age, String sex, String address) {
        String query = "INSERT INTO students (name, age, sex, address) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, sex);
            stmt.setString(4, address);
            stmt.executeUpdate();
            System.out.println("Student inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

   
    public void insertScholarship(String name, String description, int maxAmount, int totalAmount) {
        String query = "INSERT INTO scholarships (name, description, maxAmount, totalAmount) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, maxAmount);
            stmt.setInt(4, totalAmount);
            stmt.executeUpdate();
            System.out.println("Scholarship inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting scholarship: " + e.getMessage());
        }
    }

    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

