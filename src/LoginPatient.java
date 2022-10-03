import java.sql.SQLException;

public class LoginPatient {
    private static int tryCount = 0;
    private final static String TABLE = "Patient";
    private static boolean isLogin = false;

    public static void login(){

       if (tryCount < 3){
            try {
                Boolean resultSet = Authentification.isAuthentificated(TABLE);

                if (Authentification.islogin) {
                    // String Email = Authentification.getInformation(resultSet,"Email");
                    System.out.println("Welcome to the system");
                    System.out.println("Your Email is: " + Authentification.Email);
                    isLogin = true;
                } else {
                    System.out.println("Login failed");
                    tryCount++;
                    login();
                }
            } catch (SQLException e) {
                // e.printStackTrace();
            }
        }else{
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

    public static boolean isLogin(){
        return isLogin;
    }

}
