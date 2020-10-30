package csvconvert;



import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.opencsv.exceptions.CsvException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class Xml2Csv {

    private File stylesheet;
    private File xmlSource;

    public Xml2Csv(String style, String source){
        System.out.println(style);
        System.out.println(source);

        stylesheet = new File(style);
        xmlSource = new File(source);




    }
    public void Convert(String dest) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlSource);
        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(dest));
        transformer.transform(source, outputTarget);
        try {
            WriteRSSI_Throughput(dest);
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void WriteRSSI_Throughput(String dest) throws IOException, CsvException {
        CSVUpdate RSSIWriter = new CSVUpdate(dest);
        RandomGaussian NormRSSI = new RandomGaussian(60.0f, 1600.0f/9.0f);

        String s;

        for (int i=2; i<RSSIWriter.getNofrows() ; i++) {
            double RSSIi=NormRSSI.getGaussian();
            double Throughputi=(50*RSSIi) / 100;
            RSSIWriter.update(Double.toString(RSSIi), i, 6);
            RSSIWriter.update(Double.toString(Throughputi),i,7);
        }
    }

    public File getStylesheet() {
        return stylesheet;
    }

    public File getXmlSource() {
        return xmlSource;
    }
}
