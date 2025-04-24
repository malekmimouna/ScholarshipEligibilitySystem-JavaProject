package utils;

import gui.LandingPage;
import model.Scholarship;
import model.Student;
import file.ReportGenerator;
import file.Serializer;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
import database.DatabaseHelper;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LandingPage();

            List<Student> allStudents = DatabaseHelper.getAllStudents();
            List<Student> eligibleStudents = new java.util.ArrayList<>();

            for (Student s : allStudents) {
                List<Scholarship> eligible = DatabaseHelper.getEligibleScholarships(s);
                if (!eligible.isEmpty()) {
                    eligibleStudents.add(s);
                    System.out.println("Eligible Scholarships for " + s.getName() + ":");
                    eligible.forEach(sch -> System.out.println("✔ " + sch.getName()));
                }
            }

                // only eligible students
            try {
                ReportGenerator.generateEligibleStudentsReport(eligibleStudents, "eligible_students.txt");
                Serializer.serializeToFile(eligibleStudents, "students_data.bin");

                // Deserialize
                List<Student> restored = Serializer.deserializeFromFile("students_data.bin");
                System.out.println("Deserialized Students:");
                for (Student s : restored) {
                    System.out.println("👤 " + s.getName());
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
