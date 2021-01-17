package form;

import heatmap.HeatmapApp;
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
    HeatmapApp RSSI;
    HeatmapApp Throughput;


    public EndValues(HeatmapApp rssi, HeatmapApp throughput) {
        RSSI = rssi;
        Throughput = throughput;
    }

    public void formula (double latstart, double longstart, double angle, double speed){
        double r = 6371.0 * (Math.pow(10, 6));
        double d = speed/ r;
        double latstartradian = Math.toRadians(latstart);
        double longstartradian = Math.toRadians(longstart);

        double latendradian = Math.asin(Math.sin(latstartradian) * Math.cos(d) + Math.cos(latstartradian) * Math.sin(d) * Math.cos(angle));
        double longendradian = longstartradian + Math.atan2(Math.sin(angle) * Math.sin(d) * Math.cos(latstartradian), Math.cos(d) - Math.sin(latstartradian) * Math.sin(latendradian));

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
        Point cell = RSSI.getVal().CalculateGridCell(Coordinates);

        if ( cell!=null ) {
            if (choice.equals("RSSI"))
                return RSSI.getHeatmapCelldata((int) cell.getY(), (int) cell.getX());
            else if (choice.equals("THROUGHPUT")) {
                return Throughput.getHeatmapCelldata((int) cell.getY(), (int) cell.getX());
            }
        }
        return -1.0;
    }


//
//    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException, SQLException, ClassNotFoundException {
//        EndValues f = new EndValues(RSSI, Throughput);
//        f.formula(23.772976,37.966995, 79.748801, 1.0);
//        System.out.println(f.getlatend());
//        System.out.println(f.getlongend());
//    }
}