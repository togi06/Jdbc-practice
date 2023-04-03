package jdbc_tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {
    String dbUrl = "jdbc:oracle:thin:@3.236.39.52:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public  void    test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM departments");
        /*
         resultSet.next();
        System.out.println(resultSet.getString(2));
        */


        //display departments table in 10 - Administration -200- 1700 format

        //code for iterating for each row
        while (resultSet.next()){

            System.out.println(resultSet.getInt(1)+ " - "+ resultSet.getString(2)+ " - "+
                    resultSet.getString(3)+ " - " +resultSet.getInt(4) );

        }

        // aynı @Test içinde  2 farklı tabloyu da gösterebilirsin ardı ardına
        resultSet = statement.executeQuery("SELECT  * FROM  regions");
        while (resultSet.next()){

            System.out.println(resultSet.getInt(1)+ " - "+ resultSet.getString(2));

        }


        resultSet.close();
        statement.close();
        connection.close();
    }
 // testin isminin nasıl görünmesini istiyorsak
 // @DisplayName i kullanıyoruz, JUNIT özelliği
    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM departments");

        /*
        Methodları kullanabilmek için bunları yazmamız lazım
            Creat Statements
        1 ResultSet.Type_SCROLL_INSENSITIVE
        --> allow us to navigate up and down in query result.
        2 ResultSet.CONCUR_READ_ONLY
        --> read only , don't update the results

            ResultSet Method
        1 next --> move to row
        2 previous --> move to previous  row
        3 beforeFirst --> goes before the first row
        4 afterLast --> goes after last row
        5 getRow --> get the current row number
        6 last --> move to last row
        7 absolute --> move to specific row



         */

        // how to find how many rows we have for the query
        // firstly I have to move to last Row
        resultSet.last();
        //then get the row count
         int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //başka bir bilgiye geçmek için beforeFirst ü kullanmamız lazım
        // to move before first row after we use .last method
        resultSet.beforeFirst();

        // print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM departments");

        // get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());


        // get the resultsetmetadata
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //how many column  we have?
        int column = rsMetaData.getColumnCount();
        System.out.println(column);

        // getting column name
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));

        //rsMetadata.getColumnName(i) --> gets column name
        //rsMetadata.getColumnCount() --> total number of column
        // print all the column name dynamically
        for (int i = 1; i < column; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();



    }





}
