import java.util.Map;

/**
 * Class for controll all functions of the application
 */
public class StudentManagement {

    Database database;

    public StudentManagement(){
        database = Database.getInstance();
    }

    // Method for show students data to the user
    public void showAllStudents(){
        Map<Integer,Student> studentMap = database.getStudentHashMap();
        if(!studentMap.isEmpty()){
            for (Student std:studentMap.values()) {
                System.out.println(std);
            }
        }else{
            System.out.println("No Student in the registry");
        }
    }

    // Method for show a student data to the user
    public boolean showStudent(int regNumber){
        Map<Integer,Student> studentMap = database.getStudentHashMap();
        if(!studentMap.isEmpty()){
            for (Student std:studentMap.values()) {
                if(std.getRegistrationNumber() == regNumber){
                    System.out.println("Student Details :");
                    System.out.println(std);
                    return true;
                }
            }
        }
        return false;
    }

    // Student register function
    public void registerStudent(int regNumber,String fName,String lName,int age){
        Map<Integer,Student> studentMap = database.getStudentHashMap();
        boolean isAvailable = false;
        if(!studentMap.isEmpty()){
            for (Student std:studentMap.values()) {
                if(std.getRegistrationNumber() == regNumber){
                    System.out.println("Registration number is already in system");
                    isAvailable = true;
                }
            }
        }
        if(!isAvailable){
            database.addStudent(new Student(regNumber,fName,lName,age));
            System.out.println("Successfully registered");
        }
    }

    // Student data update function
    public void updateStudent(int regNumber,String fName,String lName,int age){
        database.addStudent(new Student(regNumber,fName,lName,age));
    }

    // Method for delete student
    public void deleteStudent(int regNumber){
        database.deleteStudent(regNumber);
    }

    // System exit function. Save all current data in XML file
    public void systemExit(){
        database.saveStudentsToFile();
    }
}
