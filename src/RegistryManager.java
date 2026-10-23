import java.io.*;
import java.util.*;

public class RegistryManager {
    private List<Student> students;

    public RegistryManager(){
        this.students = new ArrayList<>();
    }

    public void loadStudentsFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        boolean isFirstLine = true;

        while ((line = br.readLine()) != null){
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            if(line.trim().isEmpty()) continue;

            String [] parts = line.split(",");
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            double gpa = Double.parseDouble(parts[2].trim());
            String major = parts[3].trim();

            Student student = new Student(id, name, gpa, major);
            students.add(student);
        }
        br.close();
        System.out.println("Successfully loaded" + students.size() + "Students from file.");
    }

    public void DisplayStudents(){
        System.out.println("**-- The List of All Student --**");
        for(Student s: students){
            System.out.println(s);
        }
    }

    public void StudentsGroupedByMajor(){
        System.out.println("Students Grouped by Major");
        Map<String, List<String>> grouped = new HashMap<>();

        for (Student s : students){
            grouped.computeIfAbsent(s.getMajor(), k -> new ArrayList<>()).add(s.getName());
        }

        for(Map.Entry<String, List<String>> entry : grouped.entrySet()){
            System.out.println(entry.getKey() + " :" + String.join(",", entry.getValue()));
        }
    }

    public void AddStudent() throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adding New Student \n Enter your Details...");
        System.out.println("Enter your Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your Names: ");
        String name = scanner.nextLine();

        System.out.println("Enter your GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter your Major: ");
        String major = scanner.nextLine();

        FileWriter Writer = new FileWriter("Student.txt", true);
        Writer.write(id + "," + name + "," + gpa + "," + major + "\n");
        Writer.close();

        System.out.println("Student added successfully.");

        scanner.close();
    }
}
