package model;

import java.io.Serializable;

public class ScholarshipApplication implements Serializable {
    private Student student;
    private Scholarship scholarship;

    public ScholarshipApplication(Student student, Scholarship scholarship) {
        this.student = student;
        this.scholarship = scholarship;
    }

    public Student getStudent() { return student; }
    public Scholarship getScholarship() { return scholarship; }
}
