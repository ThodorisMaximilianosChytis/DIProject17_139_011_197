



import com.opencsv.exceptions.CsvException;
import csvconvert.Xml2Csv;
import heatmap.HeatmapApp;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class EdgeServer {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException, CsvException {

        String Answer;
        do {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Do you want to convert csv files? (Y/N)");
             Answer = myObj.nextLine();
             System.out.println(Answer);
        }while( !(Answer.equals("Y") || Answer.equals("N")) );

        if(Answer.equals("Y")){
            Xml2Csv Converter = new Xml2Csv("Input Data/avstyle.xsl", "Input Data/all_vehicles.xml");
            Converter.Convert("Output/all_vehicles.csv");
        }

        HeatmapApp RSSI = new HeatmapApp(6,"RSSI","./src/main/resources/Map.png");
        RSSI.CreateMap();


        HeatmapApp Throughput = new HeatmapApp(7,"Throughput","./src/main/resources/Map.png");
        Throughput.CreateMap();


    }
}