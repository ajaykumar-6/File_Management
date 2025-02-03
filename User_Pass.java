import java.io.*;
import java.util.*;
class User_Pass{
    private static boolean log = false;
    public static void main(String args[])throws Exception{
        Scanner k = new Scanner(System.in);
        File f = new File("users.txt");
        File lo = new File("loger.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        if(!lo.exists()){
            lo.createNewFile();
        }
        BufferedReader br = new BufferedReader(new FileReader(lo));
        String checkbool = br.readLine();
        if(checkbool != null && checkbool.equals("true")){
            log = true;
        }
        boolean check = true;
        while(check){
            System.out.printf("----Option-----%n1.Register%n2.Login%n3.Logout%n4.Exit%n");
            int choice = Integer.parseInt(k.nextLine());
            switch(choice){
                case 1:
                    System.out.print("Enter User Name : ");
                    String user = k.nextLine();
                    if(user_exists(f,user)){
                        System.out.println("User Name Already Exists !.....");
                        break;
                    }
                    System.out.print("Enter Password : ");
                    String pass = k.nextLine();
                    register(f,user,pass);
                    break;
                case 2:
                    if(log){
                        System.out.println("Already Some One is logged in ...");
                        break;
                    }
                    System.out.print("Enter User Name : ");
                    String user1 = k.nextLine();
                    System.out.print("Enter Password : ");
                    String pass1 = k.nextLine();
                    if(login(f,user1,pass1)){
                        System.out.println("Login Succesfull !....."+user1);
                        break;
                    }
                    else{
                        System.out.println("Invalid User Name or Password ");
                        break;
                    }
                case 3:
                   logout();
                   break;
                case 4:
                    System.out.println("Exiting !.......");
                    check = false;
                    break;
            }
        }
    }
    public static void register(File f,String user,String pass){
        try(
            FileWriter fw = new FileWriter(f,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter p = new PrintWriter(bw))
            {
            p.println(user+","+pass);
            System.out.println("Registration Successfull!.....");
            }
        catch(IOException e){
            System.out.println("Error Occured : "+e.getMessage());
        }
    }
    public static boolean user_exists(File f,String user)throws Exception{
        BufferedReader r = new BufferedReader(new FileReader(f));
        String line;
        while((line = r.readLine()) != null){
            String[] parts = line.split(",");
            if(parts[0].equals(user)){
                
                return true;
            }
        }
        r.close();
        return false;
    }
    public static boolean login(File f,String user1,String pass1)throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while((line = br.readLine()) != null){
            String[] parts = line.split(",");
            if((parts[0].equals(user1)) && (parts[1].equals(pass1))){
                PrintWriter pw = new PrintWriter(new FileWriter("loger.txt",false));
                pw.println("true");
                pw.close();
                log = true;
                return true;
            }
        }
        br.close();
        return false;
    }
    public static void logout()throws Exception{
        if(!log){
            System.out.println("No user has logged in ");
            return;
        }
        else{
            log = false;
            System.out.println("Logged Out ");
            PrintWriter pw = new PrintWriter(new FileWriter("loger.txt",false));
            pw.println("false");
            pw.close();
        }
    }
}