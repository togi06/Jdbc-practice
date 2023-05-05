package jdbc_tests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {
    String dbUrl = "jdbc:oracle:thin:@3.236.39.52:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";
    @Test
    public void test1() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM employees");

        //in order to get column names we need resultsetmetadata
        // bılgileri dinamik bır sekılde aktarabılmek için...
        ResultSetMetaData rsmd = resultSet.getMetaData();

       // lift of maps  to keep all informaiton
        List<Map<String, Object>> queryData= new ArrayList<>();

        //number of columns
        int columCount = rsmd.getColumnCount();

        // number of column
        while (resultSet.next()){
            Map<String,Object> row = new HashMap<>();
            // some code to fill the dynamically
            for (int i = 1; i < columCount; i++) {
                row.put(rsmd.getColumnName(i),resultSet.getObject(i));
            }
            
            //add ready map row to the list
            queryData.add(row);
            
            
        }


        resultSet.close();
        statement.close();
        connection.close();
    }
}
