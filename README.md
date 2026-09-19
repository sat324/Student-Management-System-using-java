# Student Academic Record Management System

A console-based **Student Academic Record Management System** developed using **Java, MySQL, and JDBC**.

This project allows users to manage student academic records through a simple command-line interface. It supports CRUD operations, student searching, marks calculation, grade calculation, attendance management, class average calculation, and performance reporting.

## Features

- Add a new student
- View all students
- Search student by roll number
- Search student by name
- Update student details
- Delete student records
- Store department and semester
- Store marks for five subjects
- Store attendance percentage
- Calculate total marks
- Calculate percentage
- Calculate grade
- Calculate class average
- Generate student performance report
- MySQL database integration using JDBC

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| MySQL | Database management |
| JDBC | Java-MySQL connectivity |
| VS Code | Development environment |
| Git & GitHub | Version control and project hosting |

## Project Structure

```text
StudentManagementSystem/
│
├── src/
│   ├── DBConnection.java
│   ├── Student.java
│   ├── StudentDAO.java
│   └── Main.java
│
├── database/
│   └── student_management.sql
│
├── .gitignore
└── README.md
```

## Database Structure

Database name:

```sql
student_management
```

Table name:

```sql
students
```

The table contains:

| Field | Data Type | Description |
|---|---|---|
| id | INT | Unique student ID |
| roll_no | VARCHAR(20) | Student roll number |
| name | VARCHAR(100) | Student name |
| department | VARCHAR(50) | Student department |
| semester | INT | Current semester |
| mark1 | DOUBLE | Subject 1 marks |
| mark2 | DOUBLE | Subject 2 marks |
| mark3 | DOUBLE | Subject 3 marks |
| mark4 | DOUBLE | Subject 4 marks |
| mark5 | DOUBLE | Subject 5 marks |
| attendance | DOUBLE | Attendance percentage |

## Grade Calculation

The application calculates the grade based on the student's percentage.

```text
90% and above  → A+
80% - 89%      → A
70% - 79%      → B
60% - 69%      → C
50% - 59%      → D
Below 50%      → F
```

## How to Run

### 1. Install Requirements

Make sure you have:

- JDK
- MySQL Server
- MySQL Workbench
- MySQL Connector/J
- VS Code or another Java IDE

### 2. Create the Database

Open MySQL Workbench and run:

```sql
CREATE DATABASE student_management;

USE student_management;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    roll_no VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    semester INT,
    mark1 DOUBLE,
    mark2 DOUBLE,
    mark3 DOUBLE,
    mark4 DOUBLE,
    mark5 DOUBLE,
    attendance DOUBLE
);
```

### 3. Configure MySQL Password

The project does not store the MySQL password directly in the Java source code.

Set a Windows environment variable:

```text
MYSQL_PASSWORD
```

Set its value to your MySQL root password.

The application reads the password using:

```java
System.getenv("MYSQL_PASSWORD");
```

### 4. Configure JDBC

Make sure MySQL Connector/J is available to the Java project.

The JDBC connection uses:

```text
jdbc:mysql://localhost:3306/student_management
```

### 5. Run the Application

Run:

```text
Main.java
```

The application will display a menu:

```text
==============================================
     STUDENT ACADEMIC RECORD SYSTEM
==============================================
1. Add Student
2. View All Students
3. Search Student
4. Update Student
5. Delete Student
6. Calculate Class Average
7. Performance Report
8. Exit
==============================================
Enter your choice:
```

## Example

When adding a student:

```text
========== ADD STUDENT ==========

Enter Roll Number: 101
Enter Name: Rahul
Enter Department: BCA
Enter Semester: 5
Enter Mark 1: 80
Enter Mark 2: 75
Enter Mark 3: 90
Enter Mark 4: 85
Enter Mark 5: 70
Enter Attendance (%): 85

Student added successfully!
```

The application calculates:

```text
Total Marks: 400
Percentage: 80%
Grade: A
```

## Security

Sensitive information such as the MySQL password should **not be committed to GitHub**.

The application uses an environment variable:

```text
MYSQL_PASSWORD
```

instead of storing the password directly in the source code.

## Learning Outcomes

This project demonstrates:

- Core Java programming
- Object-Oriented Programming
- Classes and objects
- Constructors
- Encapsulation
- Exception handling
- Collections and data handling
- JDBC
- SQL queries
- CRUD operations
- PreparedStatement
- MySQL database connectivity
- Git and GitHub

## Future Improvements

Possible future enhancements include:

- Java Swing GUI
- Login and authentication
- Admin and student roles
- Attendance reports
- Subject-wise performance analysis
- Sorting students by percentage
- Export reports to PDF
- Graphical performance charts
- Maven project structure
- Improved input validation

## Author

**Student Academic Record Management System**

Developed as a Java + MySQL academic project.