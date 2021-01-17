package mqttcom;


import JDBC.JDBC;
import form.EndValues;

import java.awt.geom.Point2D;

public class HandleMqttMessages {

    private JDBC database;
    private EndValues endValues;


    public HandleMqttMessages(JDBC _database, EndValues _endValues) {
        database = _database;
        endValues = _endValues;

    }

    void handleMessage(String s) {
//        for (int i = 0; i <= 100; i++) {
////            System.out.println(s);
//        }
        if (!s.equals("")) {
            String[] arrOfStr = s.split("@", 8);
//        //0 time, 1 id, 2 long,3 lat,4 angle,5 speed,6 RSSI,7 Throughput
//
            double time = Double.parseDouble(arrOfStr[0]);
            int id = Integer.parseInt(arrOfStr[1]);
            double lon = Double.parseDouble(arrOfStr[2]);
            double lat = Double.parseDouble(arrOfStr[3]);
            double angle = Double.parseDouble(arrOfStr[4]);
            double speed = Double.parseDouble(arrOfStr[5]);
            double RSSI = Double.parseDouble(arrOfStr[6]);
            double Throughput = Double.parseDouble(arrOfStr[7]);
//            System.out.println(time);
//            System.out.println(id);
//            System.out.println(lon);
//            System.out.println(lat);
//            System.out.println(angle);
//            System.out.println(speed);
//            System.out.println(RSSI);
//            System.out.println(Throughput);

            endValues.formula(lat,lon,angle,speed);
            System.out.println(endValues.getlatend());
            System.out.println(endValues.getlongend());
            double RSSIpre = endValues.GetpreRSSIorThroughput(new Point2D.Double(endValues.getlongend(),endValues.getlatend()),"RSSI");
            double Throughputpre =  endValues.GetpreRSSIorThroughput(new Point2D.Double(endValues.getlongend(),endValues.getlatend()),"THROUGHPUT");
            System.out.println(RSSIpre);
            System.out.println(Throughputpre);

//        database.ExecuteQuery("");

        }
    }
}