package database;

import model.Student;
import model.Scholarship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String URL = "jdbc:mariadb://localhost:3306/scholarship_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Malek456789";

    public static void insertStudent(Student s) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO students (name, gpa, family_income, age) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getName());
            stmt.setDouble(2, s.getGpa());
            stmt.setDouble(3, s.getFamilyIncome());
            stmt.setInt(4, s.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM students";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                
                students.add(new Student(
                        rs.getString("name"),
                        rs.getDouble("gpa"),
                        rs.getDouble("family_income"),
                        rs.getInt("age")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public static List<Scholarship> getAllScholarships() {
        List<Scholarship> scholarships = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM scholarships";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                scholarships.add(new Scholarship(
                        rs.getString("name"),
                        rs.getDouble("min_gpa"),
                        rs.getDouble("max_income"),
                        rs.getInt("min_age"),
                        rs.getInt("max_age")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scholarships;
    }

    public static List<Scholarship> getEligibleScholarships(Student s) {
        List<Scholarship> eligible = new ArrayList<>();
        for (Scholarship sch : getAllScholarships()) {
            boolean gpaOK = s.getGpa() >= sch.getMinGPA();
            boolean incomeOK = s.getFamilyIncome() <= sch.getMaxIncome();
            boolean ageOK = s.getAge() >= sch.getMinAge() && s.getAge() <= sch.getMaxAge();
            if (gpaOK && incomeOK && ageOK) {
                eligible.add(sch);
            }
        }
        return eligible;
    }
    public static void insertScholarship(Scholarship s) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO scholarships (name, min_gpa, max_income, min_age, max_age) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getName());
            stmt.setDouble(2, s.getMinGPA());
            stmt.setDouble(3, s.getMaxIncome());
            stmt.setInt(4, s.getMinAge());
            stmt.setInt(5, s.getMaxAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}