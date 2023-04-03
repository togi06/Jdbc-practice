package jdbc_tests;

import java.sql.*;
import java.util.Collection;
import java.util.Collections;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
                                        //benim databaseID
        String dbUrl = "jdbc:oracle:thin:@3.236.39.52:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM regions");

      /*
        //next()-- move to first row
        resultSet.next();

        //getting informaiton with column name
        System.out.println(resultSet.getString("region_name"));
        //getting informaiton with column index(stars 1)
        System.out.println(resultSet.getInt(1));
        //bilgi bir kelime olduğu için .getString kullanmak zorundayız
        System.out.println(resultSet.getString(2));

        //1- Europe
        //2- America
        System.out.println(resultSet.getInt(1) + " - " +resultSet.getString(2));

        // move to second row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " +resultSet.getString(2));

        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " +resultSet.getString(2));

        // next().  bize boolean olarak cevap verdiği için
        // while loop ile bu bilgiyi almak daha kolay olacak o yuzden tek tek yazmak yerıne boyle yaptık
        */
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " +resultSet.getString(2));

        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }
}
