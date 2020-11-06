//STEP 1. Import required packages
package JDBC;
import java.sql.*;

public class JDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "Christos";
    static final String PASS = "**********";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Create database
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE VEHICLE";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");

            //STEP 5: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            sql = "CREATE TABLE VEHICLE " +
                    "(timestep DOUBLE, " +
                    " device_id INTEGER, " +
                    " real_lat DOUBLE, " +
                    " real_long DOUBLE, " +
                    " predicted_lat DOUBLE, " +
                    " predicted_long DOUBLE, " +
                    " real_RSSI DOUBLE, " +
                    " real_throughput DOUBLE, " +
                    " predicted_RSSI DOUBLE, " +
                    " predicted_throughput DOUBLE)";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample