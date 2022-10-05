package databaes;
import java.sql.*;

public class DbConncetion {

    private  static final String databaseName = "macnss";
    private static final String user = "root";
    private static final String password = "";

    // this method to connect to database and return connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,user,password);
    }

    // this method to return statement
    public static Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }


}
