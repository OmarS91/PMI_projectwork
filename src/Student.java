/**
 * Class for the Student entity
 */
public class Student {

    private int registrationNumber;
    private String firstName;
    private String lastName;
    private int age;

    public Student(int registrationNumber,String firstName,String lastName,int age){
        this.registrationNumber = registrationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "RegistrationNumber=" + registrationNumber +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", age=" + age;
    }
}
