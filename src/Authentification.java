import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentification {

    private Scanner scanner;
    public static ResultSet isAuthentificated(String table) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Matricule Number: ");
        String matriculeNumber = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        ResultSet resultSet = Query.select("select * from "+table+" where "+ table + "MatricNo = "+matriculeNumber+"' and password = '"+password+"'");
        return resultSet;
    }

    public static String getInformation(ResultSet resultSet,String choice) throws SQLException {
        return resultSet.getString(choice);
    }
}
