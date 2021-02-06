



import JDBC.JDBC;
import com.opencsv.exceptions.CsvException;
import csvconvert.Xml2Csv;
import form.EndValues;
import heatmap.HeatmapApp;
import heatmap.values.CreateHeatMapValues;
import mqttcom.HandleMqttMessages;
import mqttcom.Publisher;
import mqttcom.Subscriber;
import org.xml.sax.SAXException;

import javax.sound.sampled.Port;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EdgeServer {
    String topic1;
    String topic2;

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException, SQLException, ClassNotFoundException, InterruptedException {



        if(DoAJob("Do you want to convert csv files?").equals("Y")){

            Xml2Csv Converter = new Xml2Csv("Input Data/avstyle.xsl", "Input Data/all_vehicles.xml");
            Converter.Convert("Output/all_vehicles.csv");
            Xml2Csv Converter1 = new Xml2Csv("Input Data/avstyle.xsl", "Input Data/vehicle_26.xml");
            Converter1.Convert("Output/vehicle_26.csv");
            Xml2Csv Converter2 = new Xml2Csv("Input Data/avstyle.xsl", "Input Data/vehicle_27.xml");
            Converter2.Convert("Output/vehicle_27.csv");

        }

        HeatmapApp RSSI = null;
        HeatmapApp Throughput = null;

        if(DoAJob("Do you want to create HeatMaps?( Heatmap Data is needed for android Communication )").equals("Y")) {

            RSSI = new HeatmapApp(6,"RSSI","./src/main/resources/Map.png");
            try {
                RSSI.CreateMap();
            } catch (CsvException e) {
                System.out.println("CSV ERROR");
                e.printStackTrace();
            }

            Throughput = new HeatmapApp(7,"Throughput","./src/main/resources/Map.png");
            try {
                Throughput.CreateMap();
            } catch (CsvException e) {
                System.out.println("CSV ERROR");
                e.printStackTrace();
            }

        }

        JDBC mysqldb = null;
        Publisher pub = null;
        Subscriber sub = null;


        if(RSSI!=null && Throughput!=null){

            if(DoAJob("Do you want to start Android communication" + " (Terminate Server using CTRL + C )").equals("Y") ) {

                //Create Database and Table
                mysqldb = new JDBC("newuser", "Sdi17_139_011_197@");
                //Disconnect


                //Default IP:PORT
                String IP = "test.mosquitto.org";
                String Port = "1883";
                Scanner scanf = new Scanner(System.in);


                if (DoAJob("Use default IP,Port " + IP + ":" + Port).equals("N")) {
                    System.out.println("Please Enter IP");
                    IP = scanf.nextLine();
                    System.out.println("Please Enter Port");
                    Port = scanf.nextLine();
                }

                //Subscribe

                pub = new Publisher(IP, Port);

                sub = new Subscriber(IP, Port, mysqldb, pub, new EndValues(RSSI.getVal(), Throughput.getVal()));


                System.out.println("Please Enter topic1 : Hint <roadinfo26>");
                sub.subscribeto(scanf.nextLine());

                System.out.println("Please Enter topic2 : Hint <roadinfo27>");
                sub.subscribeto(scanf.nextLine());
            }


        }
// -----------------CATCH CTRL C AND DISCONNECT SOURCES-------------------------------------------------
        JDBC finalMysqldb = mysqldb;
        Publisher finalPub = pub;
        Subscriber finalSub = sub;

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (finalPub !=null){
                    finalPub.Disconnect();
                }
                if (finalSub!=null){
                    finalSub.Disconnect();
                }
                if(finalMysqldb !=null ){
                    finalMysqldb.EXIT();

                }
                System.out.println("EDGESERVER OFF");
            }
        });


    }

    //Used to As a user interface
    private static String DoAJob(String question){
        String Answer;
        do {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println(question + " (Y/N)");
            Answer = myObj.nextLine();
            System.out.println(Answer);
        }while( !(Answer.equals("Y") || Answer.equals("N")) );
        return Answer;
    }



}