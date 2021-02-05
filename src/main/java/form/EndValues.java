package form;

import heatmap.HeatmapApp;
import heatmap.values.CreateHeatMapValues;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.sql.SQLException;


public class EndValues {


    private double late;
    private double longe;
    private CreateHeatMapValues RSSI;
    private CreateHeatMapValues Throughput;



    public EndValues(CreateHeatMapValues rssi, CreateHeatMapValues throughput) {
        RSSI = rssi;
        Throughput = throughput;
    }

    public String [] getValues(String[] Line){
        String [] Result = new String[8];
        Result[0] = String.valueOf(Double.parseDouble(Line[0]) + 1);
        System.out.println("ola kala mexri edw0");

        Result[1] = Line[1];

        formula(Double.parseDouble(Line[3]),Double.parseDouble(Line[2]),Double.parseDouble(Line[4]),Double.parseDouble(Line[5]));

        Result[2] = String.valueOf(longe);

        Result[3] = String.valueOf(late);

        Result[4] = Line[4];

        Result[5] = Line[5];

        Result[6] = String.valueOf(GetpreRSSIorThroughput(new Point2D.Double(longe,late),"RSSI"));

        Result[7] = String.valueOf(GetpreRSSIorThroughput(new Point2D.Double(longe,late),"THROUGHPUT"));

        return Result;

    }

    public void formula (double latstart, double longstart, double angle, double speed){
        double r = 6.371 * (Math.pow(10.0, 6.0));
        double d = speed/ r;
        double latstartradian = Math.toRadians(latstart);
        double longstartradian = Math.toRadians(longstart);
        double anglestartradian = Math.toRadians(angle);


        double latendradian = (Math.asin(Math.sin(latstartradian)) * Math.cos(d)) + (Math.cos(latstartradian) * Math.sin(d) * Math.cos(anglestartradian));
        double longendradian = longstartradian + (Math.atan2(Math.sin(anglestartradian) * Math.sin(d) * Math.cos(latstartradian), Math.cos(d) - Math.sin(latstartradian) * Math.sin(latendradian)));


        this.late = Math.toDegrees(latendradian);
        this.longe = Math.toDegrees(longendradian);
    }

    public double getlatend() {
        return late;
    }

    public double getlongend() {
        return longe;
    }

    public double GetpreRSSIorThroughput(Point2D.Double Coordinates,String choice){
        if (RSSI==null || Throughput==null)
            return -2.0;
        Point cell = RSSI.CalculateGridCell(Coordinates);

        if ( cell!=null) {
            if (choice.equals("RSSI"))
                return RSSI.getValCellinfo((int) cell.getY(), (int) cell.getX());
            else if (choice.equals("THROUGHPUT")) {
                return Throughput.getValCellinfo((int) cell.getY(), (int) cell.getX());
            }
        }
        return -1.0;
    }

}