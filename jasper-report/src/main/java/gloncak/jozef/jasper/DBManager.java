package gloncak.jozef.jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manage DB manipulation with H2 DB
 *
 * @see {@link https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm }
 */
public class DBManager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
//    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";


    Connection provideDBConnection() {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }



    }

    boolean executeUpdate(String sql) {
        boolean result = true;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = provideDBConnection();
            if (conn != null) {

                System.out.println("Before statement execution");
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("After statement execution");

                // STEP 4: Clean-up environment
                stmt.close();
                conn.close();
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            result = false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            result = false;
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                return result;
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return result;
            } //end finally try
            return result;
        } //end try
    }

}
