public class Student {
    private int studentID;
    private String name;
    private double[] grades={0,0,0,0};
    private int numberOfQuizzes =0;


    public Student(int studentID, String name){
        if (name == null || name.trim().equals(""))
            throw new IllegalArgumentException("Name cannot be null");
        this.studentID = studentID;
        this.name=name;
    }
    public Student(int studentID){
        this.studentID= studentID;
    }
    public int getNumberOfQuizzes(){ return numberOfQuizzes; }
    public int getStudentID() { return studentID;  }
    public String getName() {return name;}
    public double[] getGrades() { return grades; }
    public void addGrade(double grade) throws IllegalArgumentException{
        if (grade <0 || grade > 100)
            throw new IllegalArgumentException("Quiz score cannot be more than 100 or negative");
        else{
            grades[numberOfQuizzes] = grade;
            numberOfQuizzes++;
        }
    }
    public void  modifyGrade(double grade, int quizNum ) throws IllegalArgumentException{
        if (grade <0 || grade > 100)
            throw new IllegalArgumentException("Quiz score cannot be more than 100 or negative");
        else if (quizNum < 1 || quizNum >4)
            throw new IllegalArgumentException("There are only 4 quizzes");
        grades[quizNum-1]=grade;

    }
    public String toString( ){
        if (numberOfQuizzes==1)
            return String.format("ID: %d, Name: %s, quizzes grades: %.2f", studentID, name, grades[0]);
        else if (numberOfQuizzes==2)
            return String.format("ID: %d, Name: %s, quizzes grades: %.2f\t%.2f", studentID, name, grades[0],grades[1]);
        else if (numberOfQuizzes==3)
            return String.format("ID: %d, Name: %s, quizzes grades: %.2f\t%.2f\t%.2f", studentID, name, grades[0],grades[1], grades[2]);
        else
            return String.format("ID: %d, Name: %s, quizzes grades: %.2f\t%.2f\t%.2f\t%.2f", studentID, name, grades[0],grades[1], grades[2],grades[3]);
    }
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        else if (this.getClass() != obj.getClass())
            return false;
        else{
            Student theStudent = (Student)obj;
            return this.studentID == theStudent.studentID;
        }
    }
}