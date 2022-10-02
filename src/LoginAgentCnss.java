import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    // number of try to login
    private static int tryCount = 0;

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        if (tryCount < 3) {
            try {
                ResultSet resultSet = Query.select("select * from users where username = '" + username + "' and password = '" + password + "'");
                if (resultSet.next()) {
                    System.out.println("Login successfully");
                } else {

                }
            } catch (SQLException e) {
                System.out.println("Invalid username or password");
                tryCount++;
                login();
            }
        } else {
            System.out.println("You have exceeded the number of attempts");
            // after 30 seconds the user can try to login again
            try {
                System.out.println("Wait 30 seconds");
                Thread.sleep(30000);
                tryCount = 0;
                login();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
