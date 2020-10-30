



import csvconvert.Xml2Csv;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class EdgeServer {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {

        System.out.println("hey jeah");

        Xml2Csv Converter = new Xml2Csv("Input Data/avstyle.xsl", "Input Data/all_vehicles.xml");
        Converter.Convert("Output/all_vehicles.csv");



    }
}