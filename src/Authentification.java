import databaes.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentification {

    public static String Email;
    public static int PatientID;
    public static Boolean islogin = false;
    public static Boolean isAuthentificated(String table) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Matricule Number: ");
        String matriculeNumber = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        ResultSet resultSet = Query.select("select * from "+table+" where "+ table + "MatricNo = '"+matriculeNumber+"' and "+table+"Password = '"+password+"'");

        if (resultSet.next()) {
            Email = resultSet.getString(table+"Email");
            PatientID = resultSet.getInt(table+"ID");
            islogin = true;
        }
       // System.out.println("select * from "+table+" where "+ table + "MatricNo = '"+matriculeNumber+"' and "+table+"Password = '"+password+"'");
        return islogin;
    }

    public static String getInformation(ResultSet resultSet,String choice) throws SQLException {
        return resultSet.getString(choice);
    }

}
