import java.io.File;
import java.io.FileReader;

public class Main{

    public static void main(String [] args) throws Exception{
        RegistryManager manager = new RegistryManager();
        File file = new File("student.txt");
        FileReader fileReader = new FileReader(file);
        System.out.println("Look the file at: " + file.getAbsolutePath());


        manager.loadStudentsFromFile(file.getAbsolutePath());
        manager.DisplayStudents();
        manager.StudentsGroupedByMajor();
    }
}