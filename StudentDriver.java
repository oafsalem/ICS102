import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentDriver {
    public static void main(String[] args) throws IOException {
        Student[] students;
        students = readFile();
        Scanner kb = new Scanner(System.in);
        do {
            try {
                printMenu();
                System.out.println("Please select your choice: ");
                int choice = kb.nextInt();
                switch (choice) {
                    case 1:
                        displayGradeInfoForAllStudents(students);
                        break;
                    case 2:
                        displayGradeInfoForAStudent(students);
                        break;
                    case 3:
                        displayQuizAveragesForAllStudents(students);
                        break;
                    case 4:
                        modifyAParticularQuizForAStudent(students);
                        break;
                    case 5:
                        addQuizGradesForAQuizForAllStudents(students);
                        break;
                    case 6:
                        students = addANewStudent(students);
                        break;
                    case 7:
                        students = deleteStudent(students);
                        break;
                    case 8:
                        exit(students);
                        break;
                    default:
                        System.out.println("Please Enter a Valid Choice From 1 to 8");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                kb.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e);
            }
            pressEnter();
        } while (true);
    }

    private static Student[] readFile() throws IOException {
        int numberOfStudents = getNumberOfStudents();
        FileInputStream inStream = new FileInputStream("grades.txt");
        Scanner fscanner = new Scanner(inStream);
        Student[] students = new Student[numberOfStudents];
        for (int i = 0; i < students.length; i++) {
            students[i] = readLine(fscanner.nextLine());
            checkDuplicate(students, i);
        }
        fscanner.close();
        inStream.close();
        return students;
    }

    private static int getNumberOfStudents() throws IOException {
        FileInputStream inStream = new FileInputStream("grades.txt");
        Scanner fscanner = new Scanner(inStream);
        int numberOfStudentsCounter = 0;
        while (fscanner.hasNextLine()) {
            fscanner.nextLine();
            numberOfStudentsCounter++;
        }
        fscanner.close();
        inStream.close();
        return numberOfStudentsCounter;
    }

    private static void checkDuplicate(Student[] students, int i) {
        for (int j = i - 1; j >= 0; j--)
            if (students[i].equals(students[j])) {
                System.out.println("there is a duplicated ID in the file, please check the file then run the program again ");
                System.exit(0);
            }
    }

    private static Student readLine(String line) {
        String firstName, secondName;
        int id;
        double grade;
        Scanner stringScanner;
        stringScanner = new Scanner(line);
        id = stringScanner.nextInt();
        firstName = stringScanner.next();
        secondName = stringScanner.next();
        Student student = new Student(id, firstName + " " + secondName);
        try {
            while (stringScanner.hasNextDouble()) {
                grade = stringScanner.nextDouble();
                student.addGrade(grade);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The program can handle only up to 4 quizzes only, please edit the input file and try again");
            System.exit(0);
        }
        return student;

    }

    private static void printMenu() {
        System.out.println("1. Display Grade Info for all students\n" +
                "2. Display Grade Info for a particular student\n" +
                "3. Display quiz averages for all students\n" +
                "4. Modify a particular quiz grade for a particular student\n" +
                "5. Add quiz grades for a particular quiz for all students  \n" +
                "6. Add New Student\n" +
                "7. Delete Student  \n" +
                "8. Exit");
    }

    private static void displayGradeInfoForAllStudents(Student[] students) {
        System.out.printf("StudentID\t%-20s", "StudentName");
        for (int i = 0; i < students[0].getNumberOfQuizzes(); i++)
            System.out.printf("Quiz0%d\t", i + 1);
        System.out.println();
        for (int i=0; i< students.length; i++) {
            Student x = students[i];
            System.out.printf(x.getStudentID() + "\t\t%-20s", x.getName());
            for (int j = 0; j < students[i].getNumberOfQuizzes(); j++)
                System.out.printf("%4.1f\t", x.getGrades()[j]);
            System.out.println();
        }

    }

    private static void displayGradeInfoForAStudent(Student[] students) {
        Student lookupStudent = lookupStudent(students);
        if (lookupStudent == null)
            System.out.println("this student doesn't exist");
        else
            System.out.println(lookupStudent);
    }

    private static Student lookupStudent(Student[] students) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the ID of the student: ");
        int ID = kb.nextInt();
        Student lookupStudent = new Student(ID);
        for (int i = 0; i < students.length; i++) {
            if (lookupStudent.equals(students[i]))
                return students[i];
        }
        return null;
    }

    private static void displayQuizAveragesForAllStudents(Student[] students) {
        double[] grades;
        int numberOfQuizzes = students[0].getNumberOfQuizzes();
        double[] average = new double[students.length];
        double sum = 0;
        for (int i = 0; i < students.length; i++) {
            grades = students[i].getGrades();
            for (int k = 0; k < numberOfQuizzes; k++)
                sum += grades[k];
            average[i] = sum / numberOfQuizzes;
            sum = 0;
        }
        System.out.printf("StudentID\t%-20s\t\tAverage\n", "Student Name");
        for (int i = 0; i < students.length; i++)
            System.out.printf("%d\t\t%-20s\t\t%.1f\n", students[i].getStudentID(), students[i].getName(), average[i]);
    }

    private static void modifyAParticularQuizForAStudent(Student[] students) {
        Scanner kb = new Scanner(System.in);
        Student lookupStudent = lookupStudent(students);
        if (lookupStudent == null)
            System.out.println("student doesn't exist");
        else {
            System.out.println("please enter which quiz to modify");
            int quizNumber = kb.nextInt();
            if (quizNumber > lookupStudent.getNumberOfQuizzes())
                throw new IllegalArgumentException("The student didnt take that quiz yet");
            System.out.println("please enter the new grade");
            double grade = kb.nextDouble();
            System.out.println("before grade modification");
            System.out.println(lookupStudent);
            lookupStudent.modifyGrade(grade, quizNumber);
            System.out.println("after grade modification");
            System.out.println(lookupStudent);
        }
    }

    private static void addQuizGradesForAQuizForAllStudents(Student[] students) {
        Scanner kb = new Scanner(System.in);
        int numberOfQuizzes = students[0].getNumberOfQuizzes();
        if (numberOfQuizzes < 4) {
            System.out.printf("Please enter quiz grades for Quiz#%d\n\n", numberOfQuizzes + 1);
            double grade;
            for (int i = 0; i < students.length; i++) {
                do {
                    System.out.printf("Please enter grade for student: %d\n", students[i].getStudentID());
                    grade = kb.nextDouble();
                    if (grade < 0)
                        System.out.println("Error: grade can't be negative");
                    else if (grade > 100)
                        System.out.println("Error: grade can't be grater than 100");
                } while (grade < 0 || grade > 100);
                students[i].addGrade(grade);
            }
        } else
            System.out.println("The max number of quizzes has been reached (max:4)");
    }

    private static Student[] addANewStudent(Student[] students) {
        int numQuiz = students[0].getNumberOfQuizzes();
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the Id of the new Student: ");
        int ID = kb.nextInt();
        Student lookupStudent = new Student(ID);
        for (int i = 0; i < students.length; i++) {
            if (lookupStudent.equals(students[i]))
                throw new IllegalArgumentException("Student already exists");
        }
        System.out.println("Please Enter the first name of the student: ");
        String firstName = kb.next();
        System.out.println("Please Enter the last name of the student: ");
        String lastName = kb.next();
        Student newStudent = new Student(ID, firstName + " " + lastName);
        for (int i = 0; i < numQuiz; i++) {
            System.out.printf("Enter the grade for quiz0%d for the new student: ", i + 1);
            double grade = kb.nextDouble();
            newStudent.addGrade(grade);
        }
        System.out.println("The student have been created with the following information: ");
        System.out.println(newStudent.toString());
        Student[] clone = new Student[students.length + 1];
        for (int i = 0; i < students.length; i++) {
            clone[i] = students[i];
        }
        clone[clone.length - 1] = newStudent;
        return clone;
    }

    private static Student[] deleteStudent(Student[] students) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the Id of the Student: ");
        int ID = kb.nextInt();
        Student lookupStudent = new Student(ID);
        for (int i = 0; i < students.length; i++) {
            if (lookupStudent.equals(students[i])) {
                System.out.println("The Student with the following details have been deleted");
                System.out.println(students[i]);
                students[i] = null;
                Student[] clone = new Student[students.length - 1];
                int k = 0;
                for (int j = 0; j < clone.length; j++) {
                    if (students[j] == null)
                        k++;
                    clone[j] = students[k];
                    k++;
                }
                return clone;
            }

        }
        throw new IllegalArgumentException("Student doesn't exist");
    }

    private static void exit(Student[] students) throws IOException {
        FileOutputStream output = new FileOutputStream("grades.txt");
        PrintWriter pwriter = new PrintWriter(output);
        for (int i = 0; i < students.length - 1; i++) {
            pwriter.print(students[i].getStudentID() + "\t" + students[i].getName() + "\t");
            double[] grades = students[i].getGrades();
            for (int j = 0; j < students[i].getNumberOfQuizzes(); j++) {
                pwriter.print(grades[j] + "\t");
            }
            pwriter.println();
        }
        pwriter.print(students[students.length - 1].getStudentID() + "\t" + students[students.length - 1].getName() + "\t");
        double[] grades = students[students.length - 1].getGrades();
        for (int j = 0; j < students[students.length - 1].getNumberOfQuizzes(); j++) {
            pwriter.print(grades[j] + "\t");
        }
        pwriter.close();
        output.close();
        System.out.println("Done Printing to the file");
        System.out.println("Thanks for using our program");
        System.exit(0);
    }
    private static void pressEnter() {
        System.out.println();
        Scanner kb = new Scanner(System.in);
        System.out.println("Press Enter key to continue . . .");
        kb.nextLine();
    }
}