package model;

import java.io.Serializable;

public class Scholarship implements Serializable {
    private String name;
    private double minGPA;
    private double maxIncome;
    private int minAge;
    private int maxAge;

    public Scholarship(String name, double minGPA, double maxIncome, int minAge, int maxAge) {
        this.name = name;
        this.minGPA = minGPA;
        this.maxIncome = maxIncome;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getName() { return name; }
    public double getMinGPA() { return minGPA; }
    public double getMaxIncome() { return maxIncome; }
    public int getMinAge() { return minAge; }
    public int getMaxAge() { return maxAge; }
}

