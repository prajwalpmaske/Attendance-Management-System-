import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AttendanceManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, ArrayList<Boolean>> attendanceRecords = new HashMap<>();

        while (true) {
            System.out.println("\nAttendance Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance Report");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent(scanner, attendanceRecords);
                    break;
                case 2:
                    markAttendance(scanner, attendanceRecords);
                    break;
                case 3:
                    viewAttendanceReport(attendanceRecords);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 4.");
            }
        }
    }

    private static void addStudent(Scanner scanner, Map<String, ArrayList<Boolean>> attendanceRecords) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        if (!attendanceRecords.containsKey(studentName)) {
            attendanceRecords.put(studentName, new ArrayList<>());
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student already exists!");
        }
    }

    private static void markAttendance(Scanner scanner, Map<String, ArrayList<Boolean>> attendanceRecords) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        if (attendanceRecords.containsKey(studentName)) {
            ArrayList<Boolean> attendanceList = attendanceRecords.get(studentName);
            attendanceList.add(true);
            System.out.println("Attendance marked for " + studentName);
        } else {
            System.out.println("Student not found. Please add the student first.");
        }
    }

    private static void viewAttendanceReport(Map<String, ArrayList<Boolean>> attendanceRecords) {
        System.out.println("Attendance Report");
        for (Map.Entry<String, ArrayList<Boolean>> entry : attendanceRecords.entrySet()) {
            String studentName = entry.getKey();
            ArrayList<Boolean> attendanceList = entry.getValue();
            int presentCount = (int) attendanceList.stream().filter(Boolean::booleanValue).count();
            int totalClasses = attendanceList.size();
            double attendancePercentage = (presentCount / (double) totalClasses) * 100;

            System.out.println(studentName + " - Attendance: " + attendancePercentage + "%");
        }
    }
}
