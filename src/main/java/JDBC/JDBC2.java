//STEP 1. Import required packages
package JDBC;
import java.sql.*;

public class JDBC2 {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/VEHICLE";

    //  Database credentials
    static final String USER = "newuser";
    static final String PASS = "Sdi1700139@";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            /* ama to table uparxei den to dhmiourgei pali
            apla afhsa tis ektupwseis gia aisthitikous logous */
            String sql = "CREATE TABLE IF NOT EXISTS VEHICLE" +
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
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBC2