import java.io.*;
import java.util.*;

// Helper class to encapsulate student data and calculation methods
class StudentData {

    private static final Scanner scanner = new Scanner(System.in);

    // class fields
    public String name;
    public long reg;
    public long lastfour;
    public int department;

    // Arrays for marks, grades, grade points, and weighted grade points (GPA components)
    public float sgpa1, sgpa2, cgpa;
    public int[] mk1 = new int[8];
    public int[] mk2 = new int[9];
    public String[] grd1 = new String[8];
    public String[] grd2 = new String[9];
    public int[] gp1 = new int[8];
    public int[] gp2 = new int[9];
    public float[] gpo1 = new float[8];
    public float[] gpo2 = new float[9];

    // Array of subject names for Semester 1
    private final String[][] SUB_NAMES_SEM1 = {
        {},
        {"Mathematics I", "Chemistry", "C Programming", "IT Essentials", "English", "C Programming Lab", "Computer Engineering Workshop Lab", "Communication Skills Lab"},
        {"Mathematics I", "Engineering Physics", "Electronic Devices and Circuits", "Network Theory and Mathematics", "Digital Logic Design", "Engineering Physics Lab", "Electronic Devices and Circuits Lab", "Digital Logic Design Lab"},
        {"Engineering Physics", "Python Programming", "Fundamentals of Electrical Engineering (FEE)", "Basics of Electrical Engineering (BEE)", "Mathematics I", "Engineering Physics Lab", "Python Programming Lab", "Electrical Engineering Lab"},
        {"Strength of Materials", "Mathematics I", "Engineering Physics", "Engineering Mechanics", "Engineering Graphics", "Engineering Physics Lab", "Engineering Graphics Lab", "Mechanics/Strength of Materials Lab"},
        {"Engineering Mechanics", "Civil Engineering Materials", "Engineering Graphics", "Mathematics I", "Engineering Physics", "Engineering Physics Lab", "Engineering Graphics Lab", "Civil Engineering Materials Lab"}
    };

    // Array of subject names for Semester 2
    private final String[][] SUB_NAMES_SEM2 = {
        {},
        {"Mathematics II", "Applied Physics", "Data Structures using C", "Elements of Electronic Engineering", "Digital Logical Design", "Data Structures using C Lab", "Engineering Physics Lab", "LINUX Lab"},
        {"Mathematics II", "Engineering Chemistry", "Electronic Circuit Analysis", "Computer Programming", "English", "Engineering Chemistry Lab", "Electronic Circuit Analysis Lab", "Computer Programming Lab", "English Communication Skills Lab"},
        {"C Programming", "Engineering Chemistry", "English", "Industry 4.0", "Mathematics II", "Engineering Chemistry Lab", "C Programming Lab", "English Communication Skills Lab"},
        {"Mathematics II", "Green Chemistry", "English", "Industry 4.0", "C Programming", "Chemistry Lab", "C Programming Lab", "Industry 4.0 / Workshop Lab"},
        {"Mathematics II", "Green Chemistry", "English", "Surveying and Geomatics", "C Programming", "Chemistry Lab", "C Programming Lab", "English Communication Skills Lab"}
    };

    public String[] getSubNames(int semester) {
        if (department < 1 || department > 5) return new String[0];
        if (semester == 2 && department == 2) return SUB_NAMES_SEM2[2];
        if (semester == 2 && department == 4) {
            String[] mech2 = new String[9];
            System.arraycopy(SUB_NAMES_SEM2[4], 0, mech2, 0, 7);
            mech2[7] = "Workshop/Industry 4.0 Lab";
            mech2[8] = "Extra Subject/Minor Project";
            return mech2;
        }
        return semester == 1 ? SUB_NAMES_SEM1[department] : SUB_NAMES_SEM2[department];
    }

    private int getAndCheckMark(String subjectName) {
        System.out.printf("Enter marks in %s: ", subjectName);
        int mark = 0;
        try { mark = scanner.nextInt(); }
        catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter a number.");
            scanner.next();
            mark = -1;
        }
        return CgpaCalculator.checkMark(mark);
    }

    public void conv(int[] marks, String[] grades) {
        for (int i = 0; i < marks.length; i++) {
            int m = marks[i];
            grades[i] = (m >= 91) ? "O" : (m >= 81) ? "A+" : (m >= 71) ? "A" : (m >= 61) ? "B+" : (m >= 51) ? "B" : (m >= 41) ? "C" : (m >= 33) ? "P" : "F";
        }
    }

    public void gpc(String[] grades, int[] points) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) continue;
            switch (grades[i]) {
                case "O": points[i] = 10; break;
                case "A+": points[i] = 9; break;
                case "A": points[i] = 8; break;
                case "B+": points[i] = 7; break;
                case "B": points[i] = 6; break;
                case "C": points[i] = 5; break;
                case "P": points[i] = 4; break;
                default: points[i] = 0; break;
            }
        }
    }

    public void gpoc(int[] gp, float[] gpo) {
        int length = Math.min(gp.length, gpo.length);
        for (int i = 0; i < length; i++) gpo[i] = (i < 5 ? 3.0f : 1.5f) * gp[i];
    }

    public float cgpa(float[] tgpo) {
        float sum = 0;
        for (float x : tgpo) sum += x;
        return sum / 19.5f;
    }

    public void inputSemester1(int dept) {
        String[] subjects = getSubNames(1);
        for (int i = 0; i < 8; i++) mk1[i] = getAndCheckMark(subjects[i]);
        conv(mk1, grd1);
    }

    public void inputSemester2(int dept) {
        String[] subjects = getSubNames(2);
        int len = (dept == 2 || dept == 4) ? 9 : 8;
        for (int i = 0; i < len; i++) mk2[i] = getAndCheckMark(subjects[i]);
        conv(mk2, grd2);
    }
}

// Main Controller Class
class CgpaCalculator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void clsc() { if (scanner.hasNextLine()) scanner.nextLine(); }

    public static String cln(String str) { return str.replaceAll("[^a-zA-Z .]", ""); }

    public static boolean isNum(String s) { return s != null && s.matches("\\d+"); }

    public static long chkrg(String rg) {
        while (!isNum(rg)) { System.out.print("Invalid. Try again: "); rg = scanner.next(); }
        return Long.parseLong(rg);
    }

    public static int checkMark(int m) {
        while (m < 0 || m > 100) {
            System.out.print("Invalid input. Try again: ");
            try { m = scanner.nextInt(); }
            catch (InputMismatchException e) { System.out.println("Please enter a number."); scanner.next(); m = -1; }
        }
        return m;
    }

    public static void generateReportDocument(StudentData student) {
        String deptName = switch (student.department) {
            case 1 -> "Computer Science Engineering";
            case 2 -> "Electronics Communication Engineering";
            case 3 -> "Electrical Electronic Engineering";
            case 4 -> "Mechanical Engineering";
            case 5 -> "Civil Engineering";
            default -> "Unknown Department";
        };

        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String mobilePath = "/storage/emulated/0/";
        String filename = student.name.replaceAll("\\s", "_").replaceAll("[^a-zA-Z0-9_]", "") + "_" + student.lastfour + "_Report_" + timestamp + ".txt";

        File file = new File(new File(mobilePath).exists() ? mobilePath : ".", filename);

        try (PrintWriter out = new PrintWriter(file, "UTF-8")) {
            out.println("==============================================================");
            out.printf("%35s\n", "STUDENT ACADEMIC REPORT");
            out.println("==============================================================");
            out.printf("%-15s : %s\n", "Name", student.name);
            out.printf("%-15s : %d\n", "Reg No", student.reg);
            out.printf("%-15s : %s\n", "Department", deptName);
            out.println("--------------------------------------------------------------");

            out.printf("%-40s %s\n", "SEMESTER I RESULTS", String.format("(SGPA: %.2f)", student.sgpa1));
            out.printf("%-40s | %-5s | %-4s\n", "Subject", "Grade", "GPO");
            String[] s1 = student.getSubNames(1);
            for (int i = 0; i < s1.length; i++) out.printf("%-40s | %-5s | %.2f\n", s1[i], student.grd1[i], student.gpo1[i]);

            out.println("\n--------------------------------------------------------------");
            out.printf("%-40s %s\n", "SEMESTER II RESULTS", String.format("(SGPA: %.2f)", student.sgpa2));
            String[] s2 = student.getSubNames(2);
            int len2 = (student.department == 2 || student.department == 4) ? 9 : 8;
            for (int i = 0; i < len2; i++) out.printf("%-40s | %-5s | %.2f\n", s2[i], student.grd2[i], student.gpo2[i]);

            out.println("--------------------------------------------------------------");
            out.printf("%-40s : %.2f\n", "FINAL CGPA", student.cgpa);
            out.println("==============================================================");

            System.out.printf("\n✅ Report card saved successfully at: %s\n", file.getAbsolutePath());
        } catch (IOException e) { System.err.println("❌ Error saving file: " + e.getMessage()); }
    }

    public static void main(String[] args) {
        StudentData s = new StudentData();
        System.out.print("Enter student name: ");
        s.name = cln(scanner.nextLine());

        System.out.print("Enter registration number: ");
        s.reg = chkrg(scanner.next());
        s.lastfour = s.reg % 10000;
        clsc();

        System.out.println("\nSelect your department:\n1.CSE\n2.ECE\n3.EEE\n4.MECH\n5.CIVIL");
        System.out.print("> ");
        s.department = scanner.nextInt();
        clsc();

        int c1 = 0, c2 = 0;
        while (c1 != 1 || c2 != 1) {
            System.out.print("Choose semester (1 for I-I / 2 for I-II): ");
            int ch = scanner.nextInt();
            clsc();
            if (ch == 1 && c1 == 0) { s.inputSemester1(s.department); c1 = 1; }
            else if (ch == 2 && c2 == 0) { s.inputSemester2(s.department); c2 = 1; }
            else System.out.println("Already entered or invalid choice.");
        }

        s.gpc(s.grd1, s.gp1); s.gpc(s.grd2, s.gp2);
        s.gpoc(s.gp1, s.gpo1); s.gpoc(s.gp2, s.gpo2);
        s.sgpa1 = s.cgpa(s.gpo1); s.sgpa2 = s.cgpa(s.gpo2);
        s.cgpa = (s.sgpa1 + s.sgpa2) / 2;

        generateReportDocument(s);
    }
}