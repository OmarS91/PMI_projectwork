import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Create StudentManagement object
        StudentManagement studentManagement = new StudentManagement();
        Scanner sc = new Scanner(System.in);

        MainLoop : while(true){
            System.out.println("\n1-View All Students");
            System.out.println("2-Register new student");
            System.out.println("3-Modify student");
            System.out.println("4-Delete a student");
            System.out.println("5-Exit");
            System.out.println("Please Enter number: ");
            String number = sc.nextLine();

            switch (number) {
                case "1":
                    //Show all student data
                    studentManagement.showAllStudents();
                    break;

                case "2":
                    // Take input from the user and register a student
                    System.out.println("Enter Registration number : ");
                    String regNumber=sc.nextLine();
                    if(regNumber.equals("")){System.out.println("Registration number is invalid");continue; }
                    int regNumberInt = 0;
                    try{
                        regNumberInt =  Integer.parseInt(regNumber);
                    }catch(Exception e){
                        System.out.println("Registration Number should be a number");continue;
                    }
                    System.out.println("Enter first name : ");
                    String firstName=sc.nextLine();
                    if(firstName.equals("")){System.out.println("First name is invalid");continue; }
                    System.out.println("Enter last name : ");
                    String lastName=sc.nextLine();
                    if(lastName.equals("")){System.out.println("Last name is invalid");continue; }
                    System.out.println("Enter age : ");
                    String age =sc.nextLine();
                    if(age.equals("")){System.out.println("Age is invalid");continue; }
                    int ageInt = 0;
                    try{
                        ageInt =  Integer.parseInt(age);

                    }catch(Exception e){
                        System.out.println("Age should be a number");continue;
                    }
                    studentManagement.registerStudent(regNumberInt,firstName,lastName,ageInt);
                    break;

                case "3":
                    // Take input from the user and update students
                    System.out.println("Enter Registration number : ");
                    String regNumberUp=sc.nextLine();
                    if(regNumberUp.equals("")){System.out.println("Registration number is invalid");continue; }
                    int regNumberUpInt = 0;
                    try{
                        regNumberUpInt =  Integer.parseInt(regNumberUp);
                    }catch(Exception e){
                        System.out.println("Registration Number should be a number");continue;
                    }
                    boolean success = studentManagement.showStudent(regNumberUpInt);
                    if(success){
                        System.out.println("Enter first name : ");
                        String firstNameUp=sc.nextLine();
                        if(firstNameUp.equals("")){System.out.println("First name is invalid");continue; }
                        System.out.println("Enter last name : ");
                        String lastNameUp=sc.nextLine();
                        if(lastNameUp.equals("")){System.out.println("Last name is invalid");continue; }
                        System.out.println("Enter age : ");
                        String ageUp =sc.nextLine();
                        int ageIntUp = 0;
                        try{
                            ageIntUp =  Integer.parseInt(ageUp);

                        }catch(Exception e){
                            System.out.println("Age should be a number");continue;
                        }
                        studentManagement.updateStudent(regNumberUpInt,firstNameUp,lastNameUp,ageIntUp);
                        System.out.println("Updated Successfully");
                    }else {
                        System.out.println("Invalid Registration Number");
                    }
                    break;

                case "4":
                    // Delete student
                    System.out.println("Enter Registration number : ");
                    String regNumberDel=sc.nextLine();
                    if(regNumberDel.equals("")){System.out.println("Registration number is invalid");continue; }
                    int regNumberDelInt = 0;
                    try{
                        regNumberDelInt =  Integer.parseInt(regNumberDel);
                    }catch(Exception e){
                        System.out.println("Registration Number should be a number");continue;
                    }
                    studentManagement.deleteStudent(regNumberDelInt);
                    break;

                case "5":
                    // System exit method call before exit the application
                    studentManagement.systemExit();
                    break MainLoop;

                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}

