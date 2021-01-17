package heatmap;


import com.opencsv.exceptions.CsvException;
import heatmap.graphics.AddTransparency;
import heatmap.graphics.ImageGrid;
import heatmap.graphics.ImageMerge;
import heatmap.values.CreateHeatMapValues;
import org.tc33.jheatchart.HeatChart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeatmapApp {

    private int datacolumn;
    CreateHeatMapValues Val;
    private  String Name;
    private double cellHeight;
    private double cellWidth;
    private String BackgroundMap;
    double[][] HeatmapData;

    public HeatmapApp(int _datacolumn, String _Name, String _BackgroundMap) throws IOException {
        datacolumn = _datacolumn;
        Name = _Name;
        BackgroundMap = _BackgroundMap;
        Val=null;

    }

    public void ColorHeatmapApp(double data[][]){

        HeatChart map = new HeatChart(data);

        //scale between high-low
        map.setColourScale(0.3);
        map.setHighValueColour(Color.GREEN);
        map.setLowValueColour(Color.RED);
        map.setChartMargin(0);


        map.setCellSize(new Dimension((int) cellWidth,(int)cellHeight));
        map.setAxisThickness(0);
        map.setShowXAxisValues(false);
        map.setShowYAxisValues(false);


        try {
            map.saveToFile(new File("./src/main/resources/java-heat-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openandDisplayPhoto(String filename) throws IOException {
        File file = new File(filename);

        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);


}

    public void CreateMap() throws IOException, CsvException {


        Point GRIDWD = new Point(10,4);
        Val = new CreateHeatMapValues("Output/all_vehicles.csv",new Point2D.Double(23.77539,37.9686200), new Point2D.Double(23.7647600,37.9668800),GRIDWD);
        Val.FillZ(datacolumn,3,2);

        File inFile = new File(BackgroundMap);
        BufferedImage image = ImageIO.read(inFile);
        BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        cellHeight = (double) (dest.getHeight() / GRIDWD.getY());
        cellWidth = (double) (dest.getWidth() / GRIDWD.getX());




        HeatmapData = new double[ (int) GRIDWD.getY()][(int)GRIDWD.getX()];

        Val.Zvalperc(HeatmapData);

        ColorHeatmapApp(HeatmapData);
//
        AddTransparency at = new AddTransparency("./src/main/resources/java-heat-chart.png", "./src/main/resources/transparent_"+ Name + ".png");
//
        ImageMerge please = new ImageMerge(BackgroundMap,"./src/main/resources/transparent_" + Name + ".png","./src/main/resources/" + Name + "Map.png");
//
        ImageGrid comeon =  new ImageGrid("./src/main/resources/"+ Name + "Map.png","./Output/" + Name + "Heatmap.png");
//
        openandDisplayPhoto("./Output/" + Name + "Heatmap.png");

    }

    public CreateHeatMapValues getVal() {
        return Val;
    }

    public double getHeatmapCelldata(int i,int j) {
        if (Val!=null){
            return HeatmapData[(int)Val.getGrid().getY()- i -1][j]/100 * Val.getValuesDatasum();
        }
        return -1.0;
    }
}
