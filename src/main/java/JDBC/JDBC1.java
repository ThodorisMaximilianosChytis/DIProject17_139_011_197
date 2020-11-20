//STEP 1. Import required packages
package JDBC;
import java.sql.*;

public class JDBC1 {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";

    //  Database credentials
    static final String USER = "newuser";
    static final String PASS = "Sdi1700139@";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        //STEP 2: Register JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        //STEP 4: Execute a query
        System.out.println("Creating database...");
        stmt = conn.createStatement();

        /* ama h database uparxei den thn dhmiourgei pali
        apla afhsa tis ektupwseis gia aisthitikous logous */
        String sql = "CREATE DATABASE IF NOT EXISTS VEHICLE";
        stmt.executeUpdate(sql);
        System.out.println("Database created successfully...");

        System.out.println("Goodbye!");

    }//end JDBC1
}
