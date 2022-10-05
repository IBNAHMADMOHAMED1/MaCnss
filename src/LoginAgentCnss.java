import com.mysql.cj.Session;
import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import java.util.Scanner;


public class LoginAgentCnss {
    private static int tryCount = 0;
    private static int tryCountVerifyCode = 0;
    private final static String TABLE = "AgentCnss";
    public static boolean isLogin = false;
    public static void login() {

        if (tryCount < 3) {
            try {
                Boolean resultSet = Authentification.isAuthentificated(TABLE);

                if (Authentification.islogin) {
                   //String Email = Authentification.getInformation(resultSet,"Email");
                    System.out.println("Welcome to the system");
                    System.out.println("Your Email is: "+Authentification.Email);
                    String code = generateCode();
                    String message = "Your code is: "+code;
                    String subject = "Verification code";
                    System.out.println("Check your email for the code You have 5 minutes to enter the code");
                    if (Mail.sendMail(message,subject,"ibnahmadmohamed8@gmail.com")) {
                        Boolean isCodeValid = verifyCode(code);
                        Boolean isNotExpired = checkCodeExpiration(LocalTime.now());
                        if (isNotExpired) {
                            // verify if the code is valid , 3 try max

                            while (tryCountVerifyCode < 2) {
                                if (isCodeValid) {
                                    System.out.println("Code is valid");
                                    isLogin = true;
                                    break;
                                } else {
                                    System.out.println("Code is not valid");
                                    tryCountVerifyCode++;
                                    isCodeValid = verifyCode(code);
                                }
                            }
                            if (tryCountVerifyCode == 2) {
                                wait30Seconds();
                            }

                        }
                        else {
                            System.out.println("Code expired");
                            System.out.println("Do you want to resend the code? (y/n)");
                            Scanner scanner = new Scanner(System.in);
                            String choice = scanner.nextLine();
                            if (choice.equals("y")) login();
                            else {
                                System.out.println("Goodbye");
                                System.exit(0);
                            }
                        }

                    } else System.out.println("Error sending email");

                } else {
                    System.out.println("Login failed");
                    tryCount++;
                    login();
                }
            } catch (SQLException e) {
             // e.printStackTrace();
            }
        } else {
            System.out.println("You have exceeded the number of attempts");
            wait30Seconds();

        }
    }

    // generate random code to send it to the user email to verify his account and expire after 10 minutes
    public static String generateCode() {
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }
        return code;
    }


    public static boolean verifyCode(String code) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the code: ");
        String codeInput = scanner.nextLine();
        //Boolean isNotExpired = checkCodeExpiration(
        if (codeInput.equals(code)) {
            return true;
        }
        return false;
    }

    // check if the code is expired or not
    public static boolean checkCodeExpiration(LocalTime date) {
        LocalTime now = LocalTime.now();
        if (now.isBefore(date.plusMinutes(5))) {
            return true;
        }
        return false;
    }

    public static void wait30Seconds(){
        try {
            System.out.println("Wait 30 seconds");
            Thread.sleep(30000);
            tryCount = 0;
            tryCountVerifyCode = 0;
            login();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void logout(){
        isLogin = false;
    }




}

