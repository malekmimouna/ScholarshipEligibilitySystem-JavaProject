package core;

import model.Student;
import model.Scholarship;

public class EligibilityChecker {

    public static boolean isEligible(Student student, Scholarship scholarship) {
        if (student == null || scholarship == null) {
            throw new IllegalArgumentException("Student or Scholarship cannot be null");
        }

        
        boolean meetsGPA = student.getGpa() >= scholarship.getMinAge();
        boolean meetsIncome = student.getFamilyIncome() <= scholarship.getMaxIncome();
        boolean meetsAge = student.getAge() <= scholarship.getMaxAge();

        return meetsGPA && meetsIncome && meetsAge;
    }

    public static String getEligibilityReason(Student student, Scholarship scholarship) {
        StringBuilder reason = new StringBuilder();

        if (student.getGpa() < scholarship.getMinGPA()) {
            reason.append("GPA too low. ");
        }
        if (student.getFamilyIncome() > scholarship.getMaxIncome()) {
            reason.append("Family income too high. ");
        }
        if (student.getAge() > scholarship.getMaxAge()) {
            reason.append("Student too old. ");
        }

        return reason.length() > 0 ? reason.toString() : "Eligible";
    }

}
