package JDBC;
import java.sql.*;

public class JDBCInsert {
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

    public JDBCInsert(String _USER, String _PASS ) throws ClassNotFoundException {
        USER = _USER;
        PASS = _PASS;
        conn = null;
        stmt = null;

        //Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //Open a connection to user
        OpenConnection();



        //Open connection to user/database
        DB_URL = DB_URL + DATABASEN;
        OpenConnection();

//        System.out.println("Inserting in table " + TABLEN +  " in " + DATABASEN);
//        ExecuteQuery("INSERT INTO Registration " +
//                "VALUES ("
//                + timestep + ","
//                + device_id + ","
//                + real_lat + ","
//                + real_long + ","
//                + predicted_lat + ","
//                + predicted_long + ","
//                + real_RSSI + ","
//                + real_throughput + ","
//                + predicted_RSSI + ","
//                + predicted_throughput + ")");
//        System.out.println("Done");

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

        private void ExecuteQuery(String sql){
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
