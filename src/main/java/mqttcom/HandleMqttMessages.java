package mqttcom;


import JDBC.JDBC;
import form.EndValues;
import org.apache.commons.lang3.StringUtils;

import java.awt.geom.Point2D;

public class HandleMqttMessages {

    private JDBC database;
    private EndValues endValues;
    String[] oldValues = new String[8];
    String[] newValues = new String[8];
    int Old = -1;
    int New = -1 ;



    public HandleMqttMessages(JDBC _database, EndValues _endValues) {
        database = _database;
        endValues = _endValues;
        newValues[0] = "-1";


    }

    void handleMessage(String s) {
//        for (int i = 0; i <= 100; i++) {
////            System.out.println(s);
//        }
        if (StringUtils.countMatches(s, "@")==7) {
            oldValues = s.split("@", 8);
//        //0 time, 1 id, 2 long,3 lat,4 angle,5 speed,6 RSSI,7 Throughput
//

            System.out.println(oldValues[0] + "-------" + newValues[0]);
            if (Double.parseDouble(oldValues[0])== Double.parseDouble(newValues[0])){
                System.out.println("HEREEEEEEEEEE");
                database.Insert(Double.parseDouble(oldValues[0]) ,
                        Integer.parseInt(oldValues[1]),
                        Double.parseDouble(oldValues[2]),
                        Double.parseDouble(oldValues[3]),
                        Double.parseDouble(newValues[2]),
                        Double.parseDouble(newValues[3]),
                        Double.parseDouble(oldValues[6]),
                        Double.parseDouble(oldValues[7]),
                        Double.parseDouble(newValues[6]),
                        Double.parseDouble(newValues[7]));
            }
            newValues = endValues.getValues(oldValues);
//            }
            System.out.println("OLD     NEW");
            for (int i =0; i<=7 ; i++){
                System.out.println(oldValues[i] + "     " + newValues[i]);
            }


        }
    }
}