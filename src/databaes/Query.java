package databaes;
import java.sql.*;

public class Query extends DbConncetion{
    // this class to make query to database and return result (insert,update,delete,select)
    // this method to insert data to database
    public static void insert(String query) throws SQLException {
        getStatement().executeUpdate(query);
    }
    // this method to update data to database
    public static void update(String query) throws SQLException {
        getStatement().executeUpdate(query);
    }
    // this method to delete data to database
    public static void delete(String query) throws SQLException {
        getStatement().executeUpdate(query);
    }
    // this method to select data from database
    public static ResultSet select(String query) throws SQLException {
        return getStatement().executeQuery(query);
    }

    public static Boolean isExist(String query) throws SQLException {
        ResultSet resultSet = select(query);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }


}
