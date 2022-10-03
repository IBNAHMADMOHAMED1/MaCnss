
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

}
