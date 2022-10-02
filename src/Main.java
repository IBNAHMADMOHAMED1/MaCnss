public class Main {
    public static void main(String[] args) {
       int choice = Menu.showHome();
         switch (choice) {
              case 1:
                LoginAgentCnss.login();
                break;
              case 2:
               // Register.register();
                break;
              case 3:
                System.exit(0);
                break;
              default:
                System.out.println("Invalid choice");
                break;
         }
    }
}