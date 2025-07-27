import java.io.File;
import java.util.Scanner;
class File_Management_Command_Prompt{
    public static void main(String args[]){
        Scanner k = new Scanner(System.in);
        String folder_name = "";
        boolean check = false;
        System.out.println("\n 1.Choose Path ");
        System.out.println("2.Creating new Folder : ");
        int n = k.nextInt();
        k.nextLine();
        if(n == 1){
            System.out.println("Enter Path : ");
            folder_name = k.nextLine();
            check = true;
        }
        else if(n == 2){
            System.out.print("Enter Folder Name : ");
            folder_name = k.nextLine();
            create_folder(folder_name);
            check = true;
        }
        else{
            System.out.println("Enter Valid Option !.....");
        }
        while(check){
            System.out.println("\n______Options______");
            System.out.println("1.Create New File ");
            System.out.println("2.Edit File ");
            System.out.println("3.Delete File ");
            System.out.println("4.Display Files in that Particular Folder ");
            System.out.println("5.Exit ");
            int choice = Integer.parseInt(k.nextLine());
            switch(choice){
                case 1:
                    System.out.print("Enter File Name : Ex(file_name.txt) ");
                    String file_name = k.nextLine();
                    create_file(folder_name,file_name);
                    break;
                case 2:
                    System.out.print("Enter Old File Name to Edit : Ex(file_name.txt) ");
                    String old_name = k.nextLine();
                    System.out.print("Enter New File Name to Edit : Ex(file_name.txt) ");
                    String new_name = k.nextLine();
                    edit_file(folder_name,old_name,new_name);
                    break;
                case 3:
                    System.out.print("Enter File name to delete : Ex(file_name.txt) ");
                    String delete_file = k.nextLine();
                    delete_file(folder_name,delete_file);
                    break;
                case 4:
                    display(folder_name);
                    break;
                case 5:
                    System.out.println("Exiting!.....");
                    check = false;
                    break;
                default : 
                    System.out.println("Enter Valid Option !.....");
            }
        }
    }
    public static void create_folder(String folder_name){
        //String currentDirectory = System.getProperty("user.dir");
        File f = new File( folder_name);
        if(f.exists()){
            System.out.println("Folder already Exists!.....");
            //System.out.println(f.getAbsolutePath());
            return;
        }
        if(f.mkdir()){
            System.out.println("Folder Added Sucessfully!......  "+f.getAbsolutePath());
        }
        else{
            System.out.println("Folder Not Added!.....");
        }
    }
    public static void create_file(String folder_name,String file_name){
        File f = new File(folder_name,file_name);
        if(f.exists()){
            System.out.println("File Already Exists !.....");
            return;
        }
        else{
            try {
                if(f.createNewFile()){
                    System.out.println("File Created Succesfully !.....");
                }
            } catch (Exception e) {
                System.out.println("Error Message : "+e.getMessage());
            }
        }
    }
    public static void edit_file(String folder_name,String old_name,String new_name){
        File of = new File(folder_name, old_name);
        File nf = new File(folder_name,new_name);
        if(!of.exists()){
            System.out.println("File name "+of.getName()+" Not exists !.....");
            return;
        }
        if(nf.exists()){
            System.out.println("File name "+nf.getName()+" Already exists !.....");
            return;
        }
        if(of.renameTo(nf)){
            System.out.println(nf.getName()+"Renamed Successfully !.....");
        }
        else{
            System.out.println("Error Occured !.....");
        }
    }
    public static void delete_file(String folder_name,String delete_file){
        File f = new File(folder_name,delete_file);
        if(!f.exists()){
            System.out.println("File "+f.getName()+" Not Exists!.....");
            return;
        }
        try {
            if(f.exists()){
                f.delete();
                System.out.println(f.getName()+" Deleted !......");
            }
        } catch (Exception e) {
            System.out.println("Error Occured : "+e.getMessage());
        }
    }
    public static void display(String folder_name){
        File f = new File(folder_name);
        if(!f.exists()){
            System.out.println("Folder Named "+f.getName()+" Not found ");
        }
        try {
            if(f.exists()){
                File[] x = f.listFiles();
                if(x.length == 0 ){
                    System.out.println("Folder is Empty !....");
                }
                else{
                    for(File t : x){
                        System.out.println(t.getName());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error Occured "+e.getMessage());
        }
    }
}