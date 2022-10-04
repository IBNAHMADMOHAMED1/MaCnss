public class Main {
    public static void main(String[] args) {
        int choice = Menu.showHome();
        switch (choice) {
            case 1:
                LoginAgentCnss.login();
                if (LoginAgentCnss.isLogin) {
                    System.out.println("Welcome to the system");
                }
                break;
            case 2:
                LoginPatient.login();
                if (LoginPatient.isLogin){

                }
                break;
            case 3:

                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}