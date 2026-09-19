import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    dao.viewAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    dao.calculateClassAverage();
                    break;

                case 7:
                    dao.performanceReport();
                    break;

                case 8:
                    System.out.println("\nThank you for using Student Management System!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

        } while (choice != 8);

        sc.close();
    }


    // MENU
    public static void displayMenu() {

        System.out.println("\n==============================================");
        System.out.println("     STUDENT ACADEMIC RECORD SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Calculate Class Average");
        System.out.println("7. Performance Report");
        System.out.println("8. Exit");
        System.out.println("==============================================");
    }


    // ADD STUDENT
    public static void addStudent() {

        System.out.println("\n========== ADD STUDENT ==========");

        System.out.print("Enter Roll Number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Semester: ");
        int semester = sc.nextInt();

        System.out.print("Enter Mark 1: ");
        double mark1 = sc.nextDouble();

        System.out.print("Enter Mark 2: ");
        double mark2 = sc.nextDouble();

        System.out.print("Enter Mark 3: ");
        double mark3 = sc.nextDouble();

        System.out.print("Enter Mark 4: ");
        double mark4 = sc.nextDouble();

        System.out.print("Enter Mark 5: ");
        double mark5 = sc.nextDouble();

        System.out.print("Enter Attendance (%): ");
        double attendance = sc.nextDouble();

        sc.nextLine();

        Student student = new Student(
                rollNo,
                name,
                department,
                semester,
                mark1,
                mark2,
                mark3,
                mark4,
                mark5,
                attendance
        );

        dao.addStudent(student);
    }


    // SEARCH STUDENT
    public static void searchStudent() {

        System.out.println("\n========== SEARCH STUDENT ==========");
        System.out.println("1. Search by Roll Number");
        System.out.println("2. Search by Name");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter Roll Number: ");
            String rollNo = sc.nextLine();

            dao.searchByRollNo(rollNo);

        } else if (choice == 2) {

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            dao.searchByName(name);

        } else {

            System.out.println("Invalid choice.");
        }
    }


   // UPDATE STUDENT
public static void updateStudent() {

    System.out.println("\n========== UPDATE STUDENT ==========");

    System.out.print("Enter Roll Number: ");
    String rollNo = sc.nextLine();

    System.out.println("\nWhat do you want to update?");
    System.out.println("1. Name");
    System.out.println("2. Department");
    System.out.println("3. Semester");
    System.out.println("4. Mark 1");
    System.out.println("5. Mark 2");
    System.out.println("6. Mark 3");
    System.out.println("7. Mark 4");
    System.out.println("8. Mark 5");
    System.out.println("9. Attendance");
    System.out.println("10. Update All");
    System.out.println("11. Cancel");

    System.out.print("\nEnter your choice: ");
    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {

        case 1:
            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            dao.updateName(rollNo, name);
            break;


        case 2:
            System.out.print("Enter new department: ");
            String department = sc.nextLine();

            dao.updateDepartment(rollNo, department);
            break;


        case 3:
            System.out.print("Enter new semester: ");
            int semester = sc.nextInt();
            sc.nextLine();

            dao.updateSemester(rollNo, semester);
            break;


        case 4:
            System.out.print("Enter new Mark 1: ");
            double mark1 = sc.nextDouble();
            sc.nextLine();

            dao.updateMark(rollNo, "mark1", mark1);
            break;


        case 5:
            System.out.print("Enter new Mark 2: ");
            double mark2 = sc.nextDouble();
            sc.nextLine();

            dao.updateMark(rollNo, "mark2", mark2);
            break;


        case 6:
            System.out.print("Enter new Mark 3: ");
            double mark3 = sc.nextDouble();
            sc.nextLine();

            dao.updateMark(rollNo, "mark3", mark3);
            break;


        case 7:
            System.out.print("Enter new Mark 4: ");
            double mark4 = sc.nextDouble();
            sc.nextLine();

            dao.updateMark(rollNo, "mark4", mark4);
            break;


        case 8:
            System.out.print("Enter new Mark 5: ");
            double mark5 = sc.nextDouble();
            sc.nextLine();

            dao.updateMark(rollNo, "mark5", mark5);
            break;


        case 9:
            System.out.print("Enter new Attendance (%): ");
            double attendance = sc.nextDouble();
            sc.nextLine();

            dao.updateAttendance(rollNo, attendance);
            break;


        case 10:
            updateAllStudentDetails(rollNo);
            break;


        case 11:
            System.out.println("Update operation cancelled.");
            break;


        default:
            System.out.println("Invalid choice.");
    }
}

    // DELETE STUDENT
    public static void deleteStudent() {

        System.out.println("\n========== DELETE STUDENT ==========");

        System.out.print("Enter Roll Number: ");
        String rollNo = sc.nextLine();

        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            dao.deleteStudent(rollNo);

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }

    // UPDATE ALL DETAILS
public static void updateAllStudentDetails(String rollNo) {

    System.out.println("\n========== UPDATE ALL DETAILS ==========");

    System.out.print("Enter new name: ");
    String name = sc.nextLine();

    System.out.print("Enter new department: ");
    String department = sc.nextLine();

    System.out.print("Enter new semester: ");
    int semester = sc.nextInt();

    System.out.print("Enter new Mark 1: ");
    double mark1 = sc.nextDouble();

    System.out.print("Enter new Mark 2: ");
    double mark2 = sc.nextDouble();

    System.out.print("Enter new Mark 3: ");
    double mark3 = sc.nextDouble();

    System.out.print("Enter new Mark 4: ");
    double mark4 = sc.nextDouble();

    System.out.print("Enter new Mark 5: ");
    double mark5 = sc.nextDouble();

    System.out.print("Enter new Attendance (%): ");
    double attendance = sc.nextDouble();

    sc.nextLine();

    Student student = new Student(
            rollNo,
            name,
            department,
            semester,
            mark1,
            mark2,
            mark3,
            mark4,
            mark5,
            attendance
    );

    // Uses the EXISTING DAO method
    dao.updateStudent(student);
}
}