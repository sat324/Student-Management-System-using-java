import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    // ADD STUDENT
    public void addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(roll_no, name, department, semester, " +
                "mark1, mark2, mark3, mark4, mark5, attendance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getSemester());

            ps.setDouble(5, student.getMark1());
            ps.setDouble(6, student.getMark2());
            ps.setDouble(7, student.getMark3());
            ps.setDouble(8, student.getMark4());
            ps.setDouble(9, student.getMark5());

            ps.setDouble(10, student.getAttendance());

            ps.executeUpdate();

            System.out.println("\nStudent added successfully!");

        } catch (Exception e) {
            System.out.println("\nError while adding student!");
            System.out.println(e.getMessage());
        }
    }


    // VIEW ALL STUDENTS
    public void viewAllStudents() {

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n========== ALL STUDENTS ==========");

            while (rs.next()) {

                found = true;
                displayStudent(rs);
            }

            if (!found) {
                System.out.println("No students found.");
            }

        } catch (Exception e) {
            System.out.println("Error while viewing students!");
            System.out.println(e.getMessage());
        }
    }


    // SEARCH BY ROLL NUMBER
    public void searchByRollNo(String rollNo) {

        String sql = "SELECT * FROM students WHERE roll_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rollNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n========== STUDENT FOUND ==========");
                displayStudent(rs);

            } else {

                System.out.println("\nStudent not found.");
            }

        } catch (Exception e) {
            System.out.println("Error while searching student!");
            System.out.println(e.getMessage());
        }
    }


    // SEARCH BY NAME
    public void searchByName(String name) {

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n========== SEARCH RESULTS ==========");

            while (rs.next()) {

                found = true;
                displayStudent(rs);
            }

            if (!found) {
                System.out.println("No student found.");
            }

        } catch (Exception e) {
            System.out.println("Error while searching student!");
            System.out.println(e.getMessage());
        }
    }


    //UPDATE STUDENT
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET " +
                "name = ?, department = ?, semester = ?, " +
                "mark1 = ?, mark2 = ?, mark3 = ?, mark4 = ?, mark5 = ?, " +
                "attendance = ? WHERE roll_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setInt(3, student.getSemester());

            ps.setDouble(4, student.getMark1());
            ps.setDouble(5, student.getMark2());
            ps.setDouble(6, student.getMark3());
            ps.setDouble(7, student.getMark4());
            ps.setDouble(8, student.getMark5());

            ps.setDouble(9, student.getAttendance());

            ps.setString(10, student.getRollNo());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("\nStudent updated successfully!");
            } else {
                System.out.println("\nStudent not found.");
            }

        } catch (Exception e) {
            System.out.println("Error while updating student!");
            System.out.println(e.getMessage());
        }
    }


    // DELETE STUDENT
    public void deleteStudent(String rollNo) {

        String sql = "DELETE FROM students WHERE roll_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rollNo);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("\nStudent deleted successfully!");
            } else {
                System.out.println("\nStudent not found.");
            }

        } catch (Exception e) {
            System.out.println("Error while deleting student!");
            System.out.println(e.getMessage());
        }
    }


    // CLASS AVERAGE
    public void calculateClassAverage() {

        String sql = "SELECT mark1, mark2, mark3, mark4, mark5 FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int count = 0;
            double totalPercentage = 0;

            while (rs.next()) {

                double total =
                        rs.getDouble("mark1") +
                        rs.getDouble("mark2") +
                        rs.getDouble("mark3") +
                        rs.getDouble("mark4") +
                        rs.getDouble("mark5");

                double percentage = total / 5;

                totalPercentage += percentage;
                count++;
            }

            if (count == 0) {

                System.out.println("\nNo student records available.");

            } else {

                double average = totalPercentage / count;

                System.out.println("\n========== CLASS AVERAGE ==========");
                System.out.printf("Class Average: %.2f%%%n", average);
            }

        } catch (Exception e) {
            System.out.println("Error calculating class average!");
            System.out.println(e.getMessage());
        }
    }


    // PERFORMANCE REPORT
    public void performanceReport() {

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n========== PERFORMANCE REPORT ==========");

            while (rs.next()) {

                found = true;

                double total =
                        rs.getDouble("mark1") +
                        rs.getDouble("mark2") +
                        rs.getDouble("mark3") +
                        rs.getDouble("mark4") +
                        rs.getDouble("mark5");

                double percentage = total / 5;

                String grade = calculateGrade(percentage);

                System.out.println(
                        "Roll No: " + rs.getString("roll_no") +
                        " | Name: " + rs.getString("name") +
                        " | Percentage: " + String.format("%.2f", percentage) +
                        "% | Grade: " + grade
                );
            }

            if (!found) {
                System.out.println("No student records available.");
            }

        } catch (Exception e) {
            System.out.println("Error generating performance report!");
            System.out.println(e.getMessage());
        }
    }


    // DISPLAY STUDENT
    private void displayStudent(ResultSet rs) throws Exception {

        double total =
                rs.getDouble("mark1") +
                rs.getDouble("mark2") +
                rs.getDouble("mark3") +
                rs.getDouble("mark4") +
                rs.getDouble("mark5");

        double percentage = total / 5;

        String grade = calculateGrade(percentage);

        System.out.println("----------------------------------------");
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("Roll No: " + rs.getString("roll_no"));
        System.out.println("Name: " + rs.getString("name"));
        System.out.println("Department: " + rs.getString("department"));
        System.out.println("Semester: " + rs.getInt("semester"));

        System.out.println("Mark 1: " + rs.getDouble("mark1"));
        System.out.println("Mark 2: " + rs.getDouble("mark2"));
        System.out.println("Mark 3: " + rs.getDouble("mark3"));
        System.out.println("Mark 4: " + rs.getDouble("mark4"));
        System.out.println("Mark 5: " + rs.getDouble("mark5"));

        System.out.println("Total: " + total);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Grade: " + grade);

        System.out.println(
                "Attendance: " + rs.getDouble("attendance") + "%"
        );

        System.out.println("----------------------------------------");
    }


    // GRADE CALCULATION
    private String calculateGrade(double percentage) {

        if (percentage >= 90)
            return "A+";
        else if (percentage >= 80)
            return "A";
        else if (percentage >= 70)
            return "B";
        else if (percentage >= 60)
            return "C";
        else if (percentage >= 50)
            return "D";
        else
            return "F";
    }

    // UPDATE NAME
public void updateName(String rollNo, String name) {

    String sql = "UPDATE students SET name = ? WHERE roll_no = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, rollNo);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Name updated successfully!");
        else
            System.out.println("Student not found.");

    } catch (Exception e) {
        System.out.println("Error updating name!");
        System.out.println(e.getMessage());
    }
}


// UPDATE DEPARTMENT
public void updateDepartment(String rollNo, String department) {

    String sql = "UPDATE students SET department = ? WHERE roll_no = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, department);
        ps.setString(2, rollNo);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Department updated successfully!");
        else
            System.out.println("Student not found.");

    } catch (Exception e) {
        System.out.println("Error updating department!");
        System.out.println(e.getMessage());
    }
}


// UPDATE SEMESTER
public void updateSemester(String rollNo, int semester) {

    String sql = "UPDATE students SET semester = ? WHERE roll_no = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, semester);
        ps.setString(2, rollNo);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Semester updated successfully!");
        else
            System.out.println("Student not found.");

    } catch (Exception e) {
        System.out.println("Error updating semester!");
        System.out.println(e.getMessage());
    }
}


// UPDATE MARK
public void updateMark(String rollNo, String markColumn, double mark) {

    String sql = "UPDATE students SET " + markColumn + " = ? WHERE roll_no = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDouble(1, mark);
        ps.setString(2, rollNo);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Mark updated successfully!");
        else
            System.out.println("Student not found.");

    } catch (Exception e) {
        System.out.println("Error updating mark!");
        System.out.println(e.getMessage());
    }
}


// UPDATE ATTENDANCE
public void updateAttendance(String rollNo, double attendance) {

    String sql = "UPDATE students SET attendance = ? WHERE roll_no = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDouble(1, attendance);
        ps.setString(2, rollNo);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Attendance updated successfully!");
        else
            System.out.println("Student not found.");

    } catch (Exception e) {
        System.out.println("Error updating attendance!");
        System.out.println(e.getMessage());
    }
}
}