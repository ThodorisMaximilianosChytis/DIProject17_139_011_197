package mqttcom;


import JDBC.JDBC;

public class HandleMqttMessages {

    private JDBC database;


    public HandleMqttMessages(JDBC _database){
        database = _database;

    }
    void handleMessage(String s ){
        for (int i=0; i<=100 ; i++){
//            System.out.println(s);
        }

//        database.ExecuteQuery("");

    }}
