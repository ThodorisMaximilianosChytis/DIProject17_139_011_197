package form;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;


public class Formula {


    private double late;
    private double longe;

    public Formula ( double latstart, double longstart, double angle, double speed ) {

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


//    public Formula formula ( double latstart, double longstart, double angle, double speed ) {
//        long double d = speed/R;
//        double latstartradian = Math.toRadians(latstart);
//        double longstartradian = Math.toRadians(longstart);
//
//        double latendradian = Math.asin(Math.sin(latstartradian) * Math.cos(d) + Math.cos(latstartradian) * Math.sin(d) * Math.cos(angle));
//        double longendradian = longstartradian + Math.atan2(Math.sin(angle) * Math.sin(d) * Math.cos(latstartradian), Math.cos(d) - Math.sin(latstartradian) * Math.sin(latendradian));
//
//        return new Formula(Math.toDegrees(latendradian),Math.toDegrees(longendradian));
//    }
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException, SQLException, ClassNotFoundException {
        Formula f = new Formula(23.772976,37.966995, 79.748801, 1.0);
        System.out.println(f.getlatend());
        System.out.println(f.getlongend());
    }
}