public class Main {
    public static void main(String[] args) {
        baseMenu();
    }

    private static void baseMenu() {
        int choice = Menu.showHome();
        switch (choice) {
            case 1:
                LoginAgentCnss.login();
                if (LoginAgentCnss.isLogin) {
                    System.out.println("Welcome to the system");
                    caseAgentCnss(Menu.showMenuAgentCnss());
                }
                break;
            case 2:
                LoginPatient.login();
                if (LoginPatient.isLogin()){

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

    // method to case agent cnss
    public static void caseAgentCnss(int choice){

        switch (choice) {
            case 1:
                System.out.println("Add a new Folder Patient");
                Folder.addNewFolder();
                caseAgentCnss(Menu.showMenuAgentCnss());
                break;
            case 2:
                System.out.println("Validate a Folder Patient");
                Folder.validateFolder();
                caseAgentCnss(Menu.showMenuAgentCnss());
                break;
            case 3:
                System.out.println("Search a Folder Patient");
                caseAgentCnss(Menu.showMenuAgentCnss());
                break;
            case 4:
                System.out.println("Logout");
                LoginAgentCnss.logout();
                baseMenu();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}