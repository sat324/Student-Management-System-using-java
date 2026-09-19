public class Student {

    private int id;
    private String rollNo;
    private String name;
    private String department;
    private int semester;

    private double mark1;
    private double mark2;
    private double mark3;
    private double mark4;
    private double mark5;

    private double attendance;

    // Constructor
    public Student(String rollNo, String name, String department,
                   int semester, double mark1, double mark2,
                   double mark3, double mark4, double mark5,
                   double attendance) {

        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.mark4 = mark4;
        this.mark5 = mark5;
        this.attendance = attendance;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getMark1() {
        return mark1;
    }

    public void setMark1(double mark1) {
        this.mark1 = mark1;
    }

    public double getMark2() {
        return mark2;
    }

    public void setMark2(double mark2) {
        this.mark2 = mark2;
    }

    public double getMark3() {
        return mark3;
    }

    public void setMark3(double mark3) {
        this.mark3 = mark3;
    }

    public double getMark4() {
        return mark4;
    }

    public void setMark4(double mark4) {
        this.mark4 = mark4;
    }

    public double getMark5() {
        return mark5;
    }

    public void setMark5(double mark5) {
        this.mark5 = mark5;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    // Calculate total marks
    public double getTotal() {
        return mark1 + mark2 + mark3 + mark4 + mark5;
    }

    // Calculate percentage
    public double getPercentage() {
        return getTotal() / 5;
    }

    // Calculate grade
    public String getGrade() {

        double percentage = getPercentage();

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
}