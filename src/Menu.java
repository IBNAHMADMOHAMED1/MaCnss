
import java.util.Scanner;

public class Menu {
    public static  int showHome() {
        System.out.println("1- Login");
        System.out.println("2- Login as a patient");
        System.out.println("3- Register");
        System.out.println("4- Exit");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
    public static int showMenuAgentCnss()
    {
        System.out.println("*** Welcome to the Dashboard *** \n");
        System.out.println("1- Add a new Folder Patient");
        System.out.println("2- Validate a Folder Patient");
        System.out.println("3- Search a Folder Patient");
        System.out.println("4- Logout");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
    public static int showMenuPatient()
    {
        System.out.println("*** Welcome to the Dashboard *** \n");
        System.out.println("1- Check All Folders");
        System.out.println("2- Check specific folder");
        System.out.println("3- Logout");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }

}
