import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Behave as a database for the application
 * Contains student data
 */
public class Database {

    private static Database instance; // Static instance of this class
    private Map<Integer,Student> studentHashMap; // keep member details with a key as unique MemberID
    private static final String FILE_PATH = "Students.xml"; // XML file location

    private Database() {
        this.studentHashMap = new HashMap<>();
        readRegisterDetails();
    }
    // Method for get static instance of Database class.
    public static Database getInstance(){
        if(instance==null){
            synchronized (Database.class){
                if(instance==null){
                    instance=new Database();
                }
            }
        }
        return instance;
    }

    // Read data from the XML file and put data into Maps. Key value is student registration number
    private void readRegisterDetails() {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILE_PATH));

            doc.getDocumentElement().normalize();

            doc.getDocumentElement().getNodeName();

            // Get Student List
            NodeList list = doc.getElementsByTagName("Student");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // get data of the student
                    String registerNumber = element.getElementsByTagName("registrationNumber").item(0).getTextContent();
                    String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                    String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                    String age = element.getElementsByTagName("age").item(0).getTextContent();
                    // put data in Hashmap
                    this.studentHashMap.put(Integer.parseInt(registerNumber),new Student(Integer.parseInt(registerNumber),firstname,lastname,Integer.parseInt(age)));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
           System.out.println("Student Registration File is not found");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Method for add student to the Hashmap
    public void addStudent(Student student){
        this.studentHashMap.put(student.getRegistrationNumber(),student);
    }

    // Method for delete student from Hashmap
    public void deleteStudent(int regNumber){
        Student student = this.studentHashMap.get(regNumber);
        if(student == null){
            System.out.println("Student with registration number:"+regNumber+" does not exists");
        }else{
            this.studentHashMap.remove(regNumber);
            System.out.println("Student with registration number:"+regNumber+" successfully removed");
        }
    }

    // Write Hashmap data to the XML file
    public void saveStudentsToFile(){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("EducationInstitute");
            doc.appendChild(rootElement);

            for (Student std : this.studentHashMap.values()) {
                // add xml elements
                Element student = doc.createElement("Student");
                // add staff to root
                rootElement.appendChild(student);

                Element regNumber = doc.createElement("registrationNumber");
                regNumber.setTextContent(Integer.toString(std.getRegistrationNumber()));
                student.appendChild(regNumber);

                Element fName = doc.createElement("firstname");
                fName.setTextContent(std.getFirstName());
                student.appendChild(fName);

                Element lName = doc.createElement("lastname");
                lName.setTextContent(std.getLastName());
                student.appendChild(lName);

                Element age = doc.createElement("age");
                age.setTextContent(Integer.toString(std.getAge()));
                student.appendChild(age);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            transformer.transform(source, result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Method for get student data map
    public Map<Integer, Student> getStudentHashMap() {
        return studentHashMap;
    }
}
