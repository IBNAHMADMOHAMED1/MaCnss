import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginAgentCnss {
    private static int tryCount = 0;
    private static boolean isLogin = false;
    public static void login() {

        if (tryCount < 3) {
            try {
                if (Authentification.isAuthentificated("agentcnss")) {
                    isLogin = true;
                    System.out.println("Login success");
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
}

