CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    gpa DOUBLE,
    income DOUBLE,
    email VARCHAR(100)
);

CREATE TABLE scholarships (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    min_gpa DOUBLE,
    max_income DOUBLE
);
