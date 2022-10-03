import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class LoginAgentCnss {
    private static int tryCount = 0;
    private final static String TABLE = "AgentCnss";
    private static boolean isLogin = false;
    public static void login() {

        if (tryCount < 3) {
            try {
                if (Authentification.isAuthentificated(TABLE).next()) {
                    String Email = Authentification.getInformation(Authentification.isAuthentificated(TABLE),"Email");
                    System.out.println("Welcome to the system");
                    System.out.println("Your Email is: "+Email);
                    isLogin = true;
                } else {
                    System.out.println("Login failed");
                    tryCount++;
                    login();
                }
            } catch (SQLException e) {
                System.out.println("Login failed");
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
    public static boolean isLogin() {
        return isLogin;
    }


}

