import java.io.*;
import java.util.*;
class Student
{
    String name;
    String roll_no;
    String age;
    String course;
    public Student(String name,String roll_no,String age,String course)
    {
        this.name = name;
        this.roll_no = roll_no;
        this.age = age;
        this.course = course;
    }
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return roll_no;
    }
    public String getAge()
    {
        return age;
    }
    public String getCourse()
    {
        return course;
    }
    public String toCSV()
    {
        return name + ","+roll_no+","+age+","+course;
    }
    public static Student fromCSV(String line)
    {
        String[] parts = line.split(",");
        return new Student(parts[0],parts[1],parts[2],parts[3]);
    }
    @Override
    public String toString()
    {
        return "Name : "+name+", Roll Number : "+roll_no+", Age : "+", Course : "+course;
    }
}
public class StudentRecordSystem
{
    static ArrayList<Student> details = new ArrayList<>();
    static Scanner k = new Scanner(System.in);
    static final String file_name = "StudentsData.csv";
    public static void main(String args[]) throws Exception
    {
        loadFromFile();
        String choice;
        do{
            System.out.print("_____Student Record Management_____\n1.Add Student\n2.Delete Student\n3.Update Student\n4.View All Students\n5.Search Student\n6.Exit\nEnter Option : ");
            choice = k.nextLine();
            switch(choice)
            {
                case "1": addStudent(); break;
                case "2": removeStudent(); break;
                case "3": updateStudent(); break;
                case "4": viewAllStudent(); break;
                case "5": searchStudent(); break;
                case "6": System.out.println("Exiting!...."); break;
                default : System.out.println("Enter Correct Option");
            }
        }while(!choice.equals("6"));
    }
    public static void loadFromFile() throws Exception
    {
        File file = new File(file_name);
        if(!file.exists())
        {
            file.createNewFile();
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(file_name));
        String line;
        details.clear();
        while((line = br.readLine()) != null)
        {
            if(!line.trim().isEmpty())
            {
                details.add(Student.fromCSV(line));
            }
        }
        br.close();
    }
    public static void saveToFile() throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file_name));
        for(Student s : details)
        {
            bw.write(s.toCSV());
            bw.newLine();
        }
        bw.close();
    }
    public static void addStudent()
    {
        System.out.print("Enter Student RollNumber : ");
        String roll = k.nextLine();
        for(Student s : details)
        {
            if(s.roll_no.equalsIgnoreCase(roll))
            {
                System.out.println(s.getId()+"Student with same RollNumber can't exist!...");
                return;
            }
        }
        System.out.print("Enter Student Name : ");
        String name = k.nextLine();
        System.out.print("Enter Student Age : ");
        String age = k.nextLine();
        System.out.print("Enter Student Course : ");
        String course = k.nextLine();
        details.add(new Student(name,roll,age,course));
        System.out.println("Student Added!...");
        try{
            saveToFile();
        }
        catch(Exception e)
        {
            System.out.println("Error in Saving Data!...");
        }
    }
    public static void removeStudent()
    {
        System.out.print("Enter Student RollNumber : ");
        String roll = k.nextLine();
        Iterator<Student> iterator = details.iterator();
        while(iterator.hasNext())
        {
            Student s = iterator.next();
            if(s.roll_no.equalsIgnoreCase(roll))
            {
                iterator.remove();
                System.out.println("Student Removed!...");
                try
                {
                    saveToFile();
                }
                catch(Exception e)
                {
                    System.out.println("Error in Deleting Data!...");
                }
                return;
            }
        }
        System.out.println("No Student Found!...");
    }
    public static void updateStudent()
    {
        System.out.print("Enter Student RollNumber : ");
        String roll = k.nextLine();
        for(Student s : details)
        {
            if(s.roll_no.equalsIgnoreCase(roll))
            {
                System.out.print("Enter New Name : ");
                s.name = k.nextLine();
                System.out.print("Enter New RollNumber : ");
                s.roll_no = k.nextLine();
                System.out.print("Enter New Age : ");
                s.age = k.nextLine();
                System.out.print("Enter New Course : ");
                s.course = k.nextLine();
                System.out.println("Updated Successfully!...");
                try
                {
                    saveToFile();
                }
                catch(Exception e)
                {
                    System.out.println("Error in Upadating Data!...");
                }
                return;
            }
        }
        System.out.println("No Such Student Found!...");
    }
    public static void viewAllStudent()
    {
        if(details.isEmpty())
        {
            System.out.println("No Students Found!...");
            return;
        }
        for(Student s : details)
        {
            System.out.println(s);
        }
    }
    public static void searchStudent()
    {
        System.out.print("Enter RollNumber : ");
        String roll = k.nextLine();
        for(Student s : details)
        {
            if(s.roll_no.equalsIgnoreCase(roll))
            {
                System.out.println("Student Found "+s);
                return;
            }
        }
        System.out.println("Student Not Found!...");
    }
}
