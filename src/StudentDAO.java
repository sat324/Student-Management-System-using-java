import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {

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

            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println("Error while adding student!");
            e.printStackTrace();
        }
    }
}