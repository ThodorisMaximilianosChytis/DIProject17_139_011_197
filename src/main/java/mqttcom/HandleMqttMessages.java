package mqttcom;


import JDBC.JDBC;
import distance.DistanceCalculator;
import form.EndValues;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;


public class HandleMqttMessages {

    String topic;
    private JDBC database;
    private EndValues endValues;
    String[] oldValues = new String[8];
    String[] newValues = new String[8];
    Publisher publisher;
    int Old = -1;
    int New = -1 ;
    double DistanceSum=0;
    int numofDistances=0;


    public HandleMqttMessages(JDBC _database, Publisher _pub, EndValues _endValues, String _topic) {
        database = _database;
        endValues = _endValues;
        publisher = _pub;
        newValues[0] = "-1";
        topic = _topic;

    }

    void handleMessage(String mess) {
//        for (int i = 0; i <= 100; i++) {
////            System.out.println(s);
//        }
        if (StringUtils.countMatches(mess, "@")==7) {

            oldValues = mess.split("@", 8);
//        //0 time, 1 id, 2 long,3 lat,4 angle,5 speed,6 RSSI,7 Throughput

            //Error distance between predicted and real
            if (!newValues[0].equals("-1")) {
                numofDistances++;
                DistanceSum += 1000 * DistanceCalculator.distance(Double.parseDouble(oldValues[3]),
                        Double.parseDouble(oldValues[2]),
                        Double.parseDouble(newValues[3]),
                        Double.parseDouble(newValues[2]),
                        "K");
            }
            System.out.println(topic + " %% " + DistanceSum);

            //Insert into database
            System.out.println(oldValues[0] + "-------" + newValues[0]);
            if (Double.parseDouble(oldValues[0])== Double.parseDouble(newValues[0])){
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

            //predict
            newValues = endValues.getValues(oldValues);
//            }

            //create message
            String sendtoandroid=newValues[0];
            for (int i = 2; i < newValues.length ; i++){
                sendtoandroid =  sendtoandroid + "@" + newValues[i];
                System.out.println(i);
                if (i==3){
                    i+=2;
                }

            }
            //sent message to android
            System.out.println(sendtoandroid);
            publisher.publishto(topic,sendtoandroid);
            System.out.println("OLD     NEW");
            for (int i =0; i<=7 ; i++){
                System.out.println(oldValues[i] + "     " + newValues[i]);
            }


        }
    }

    public double getMeanDistanceError(){
        if (numofDistances==0)
            return 0;
        else
            return DistanceSum/numofDistances;
    }

}