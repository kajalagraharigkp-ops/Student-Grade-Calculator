
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Student Marks and Grade Calculator (console application).
 *
 * Flow: ask for number of subjects -> read marks (0-100, validated)
 *       -> compute total, average percentage, grade -> display report.
 */
public class StudentGradeCalculator {

    private static final double MAX_MARKS = 100.0;

    // ---------- Calculation logic ----------

    /** Total Marks = sum of marks in all subjects. */
    static double calculateTotal(List<Double> marks) {
        double total = 0;
        for (double m : marks) {
            total += m;
        }
        return total;
    }

    /** Average Percentage = Total Marks / Number of Subjects (rounded to 2 decimals). */
    static double calculateAverage(double total, int subjects) {
        if (subjects <= 0) {
            return 0.0;
        }
        double avg = total / subjects;
        return Math.round(avg * 100.0) / 100.0;
    }

    /** Grade based on the average percentage. */
    static String calculateGrade(double percentage) {
        if (Double.isNaN(percentage) || Double.isInfinite(percentage) || percentage < 0) {
            return "F";
        }
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        if (percentage >= 40) return "E";
        return "F";
    }

    // ---------- Input handling with validation ----------

    /** Reads a whole number >= 1, repeating until valid. */
    static int readSubjectCount(Scanner sc) {
        while (true) {
            System.out.print("Enter number of subjects: ");
            String line = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(line);
                if (n >= 1) return n;
                System.out.println("  Error: number of subjects must be at least 1.");
            } catch (NumberFormatException e) {
                System.out.println("  Error: please enter a whole number (e.g. 5).");
            }
        }
    }

    /** Reads marks between 0 and 100, repeating until valid. */
    static double readMarks(Scanner sc, int subjectNo) {
        while (true) {
            System.out.printf("Enter marks for Subject %d (out of 100): ", subjectNo);
            String line = sc.nextLine().trim();
            try {
                double m = Double.parseDouble(line);
                if (Double.isNaN(m) || Double.isInfinite(m)) {
                    System.out.println("  Error: please enter a valid number.");
                } else if (m < 0 || m > MAX_MARKS) {
                    System.out.println("  Error: marks must be between 0 and 100. You entered " + line + ".");
                } else {
                    return m;
                }
            } catch (NumberFormatException e) {
                System.out.println("  Error: '" + line + "' is not a number. Enter marks between 0 and 100.");
            }
        }
    }

    // ---------- Output ----------

    static void displayResults(List<Double> marks, double total, double average, String grade) {
        int n = marks.size();
        System.out.println();
        System.out.println("=======================================");
        System.out.println("            RESULT SUMMARY             ");
        System.out.println("=======================================");
        for (int i = 0; i < n; i++) {
            System.out.printf("Subject %-3d : %.2f / 100%n", i + 1, marks.get(i));
        }
        System.out.println("---------------------------------------");
        System.out.printf("Total Marks        : %.2f / %.0f%n", total, n * MAX_MARKS);
        System.out.printf("Average Percentage : %.2f%%%n", average);
        System.out.println("Final Grade        : " + grade);
        System.out.println("=======================================");
    }

    // ---------- Main ----------

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Student Marks and Grade Calculator ===\n");

        boolean again;
        do {
            int subjects = readSubjectCount(sc);
            List<Double> marks = new ArrayList<>();
            for (int i = 1; i <= subjects; i++) {
                marks.add(readMarks(sc, i));
            }

            double total = calculateTotal(marks);
            double average = calculateAverage(total, subjects);
            String grade = calculateGrade(average);
            displayResults(marks, total, average, grade);

            System.out.print("\nCalculate for another student? (y/n): ");
            again = sc.nextLine().trim().equalsIgnoreCase("y");
            System.out.println();
        } while (again);

        System.out.println("Thank you for using the calculator!");
        sc.close();
    }
}
