package file;

import model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public static void generateEligibleStudentsReport(List<Student> students, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("=== List of Eligible Students ===\n\n");
            for (Student student : students) {
                writer.write("Name: " + student.getName() + "\n");
                writer.write("GPA: " + student.getGpa() + "\n");
                writer.write("Income: " + student.getFamilyIncome() + "\n");
                writer.write("Age: " + student.getAge() + "\n");
                writer.write("-----------------------------\n");
            }
        }
    }
}
