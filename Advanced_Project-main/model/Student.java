package model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private double gpa;
    private double familyIncome;
    private int age;

    public Student(String name, double gpa, double familyIncome, int age) {
        this.name = name;
        this.gpa = gpa;
        this.familyIncome = familyIncome;
        this.age = age;
    }

    public String getName() { return name; }
    public double getGpa() { return gpa; }
    public double getFamilyIncome() { return familyIncome; }
    public int getAge() { return age; }
}
