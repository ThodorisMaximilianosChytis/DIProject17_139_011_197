//STEP 1. Import required packages
package JDBC;
import java.sql.*;

public class JDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/";

    //  Database credentials
    static String USER ;                    //newuser
    static String PASS ;               //Sdi17_139_011_197@
    static Connection conn;
    static Statement stmt;
    static final String DATABASEN = "VEHICLES";
    private final String TABLEN = "VEHICLE";

    public JDBC(String _USER, String _PASS ) throws ClassNotFoundException{
        USER = _USER;
        PASS = _PASS;
        conn = null;
        stmt = null;

        //Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //Open a connection to user
        OpenConnection();


        System.out.println("Creating database:" + DATABASEN);
        ExecuteQuery("CREATE DATABASE IF NOT EXISTS "  + DATABASEN);
        System.out.println("Database created successfully...");

        //Open connection to user/database
        DB_URL=DB_URL + DATABASEN;
        OpenConnection();

        System.out.println("Creating table " + TABLEN +  " in " + DATABASEN);
        ExecuteQuery("CREATE TABLE IF NOT EXISTS "+ TABLEN +
                "(timestep DOUBLE, " +
                " device_id INTEGER, " +
                " real_lat DOUBLE, " +
                " real_long DOUBLE, " +
                " predicted_lat DOUBLE, " +
                " predicted_long DOUBLE, " +
                " real_RSSI DOUBLE, " +
                " real_throughput DOUBLE, " +
                " predicted_RSSI DOUBLE, " +
                " predicted_throughput DOUBLE);");

    }

    public void Insert(double timestep, int device_id, double real_lat, double real_long, double predicted_lat, double predicted_long
    , double real_RSSI, double real_throughput, double predicted_RSSI, double predicted_throughput) {
        System.out.println("Inserting in table " + TABLEN +  " in " + DATABASEN);
        System.out.println("INSERT INTO VEHICLE(timestep," +
                "device_id," +
                "real_lat," +
                "real_long" +
                ",predicted_lat" +
                ",predicted_long" +
                ",real_RSSI" +
                ",real_throughput" +
                ",predicted_RSSI" +
                ",predicted_throughput)" +
                "VALUES("
                + timestep + ","
                + device_id + ","
                + real_lat + ","
                + real_long + ","
                + predicted_lat + ","
                + predicted_long + ","
                + real_RSSI + ","
                + real_throughput + ","
                + predicted_RSSI + ","
                + predicted_throughput + ");");

        ExecuteQuery("INSERT INTO VEHICLE(timestep," +
                "device_id," +
                "real_lat," +
                "real_long" +
                ",predicted_lat" +
                ",predicted_long" +
                ",real_RSSI" +
                ",real_throughput" +
                ",predicted_RSSI" +
                ",predicted_throughput)" +
                " VALUES("
                + timestep + ","
                + device_id + ","
                + real_lat + ","
                + real_long + ","
                + predicted_lat + ","
                + predicted_long + ","
                + real_RSSI + ","
                + real_throughput + ","
                + predicted_RSSI + ","
                + predicted_throughput + ");");
        System.out.println("Done");
    }

    private void OpenConnection(){
        System.out.println("Connecting...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            System.out.println("Could not connect to " + DB_URL + " " + USER);
            throwables.printStackTrace();
        }
    }

    public void ExecuteQuery(String sql){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException throwables) {
            System.out.println("SQLQueryError");
            throwables.printStackTrace();
        }
    }

    public void EXIT(){
        //close resources
        try{
            if(stmt!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        System.out.println("Disconnect db!");
    }

}
