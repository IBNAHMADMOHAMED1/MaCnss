import java.sql.*;
import databaes.Query;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            ResultSet resultSet = Query.select("select * from users");
            List<String> users = new ArrayList<>(); // this list to store all users
            while (resultSet.next()) {
                users.add(resultSet.getString("username"));
            }
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}