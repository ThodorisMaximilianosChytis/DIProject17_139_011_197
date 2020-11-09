package heatmap;


import com.opencsv.exceptions.CsvException;
import heatmap.graphics.AddTransparency;
import heatmap.graphics.ImageGrid;
import heatmap.graphics.ImageMerge;
import heatmap.values.CreateHeatMapValues;
import org.tc33.jheatchart.HeatChart;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class HeatmapApp {

    private int datacolumn;
    private  String Name;

    public HeatmapApp(int _datacolumn, String _Name){
        datacolumn = _datacolumn;
        Name = _Name;
    }

    public void ColorHeatmapApp(double data[][]){

        HeatChart map = new HeatChart(data);

        map.setColourScale(0.3);
        map.setHighValueColour(Color.GREEN);
//        map.setLowValueColour(Color.RED);
        map.setChartMargin(0);
        map.setCellSize(new Dimension(133,68));
        map.setAxisThickness(0);
        map.setShowXAxisValues(false);
        map.setShowYAxisValues(false);


        try {
            map.saveToFile(new File("./src/main/resources/java-heat-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void CreateMap() throws IOException, CsvException {
        Point GRIDWD = new Point(10,4);
        CreateHeatMapValues Val = new CreateHeatMapValues("Output/all_vehicles.csv",new Point2D.Double(23.77539,37.9686200), new Point2D.Double(23.7647600,37.9668800),GRIDWD);
        Val.FillZ(datacolumn,2,3);

        double[][] HeatmapData;
        HeatmapData = new double[ (int) GRIDWD.getY()][(int)GRIDWD.getX()];

        Val.Zvalperc(HeatmapData);

        ColorHeatmapApp(HeatmapData);

        AddTransparency at = new AddTransparency("./src/main/resources/java-heat-chart.png", "./src/main/resources/transparent_"+ Name + ".png");

        ImageMerge please = new ImageMerge("./src/main/resources/Map.png","./src/main/resources/transparent_" + Name + ".png","./src/main/resources/" + Name + "Map.png");

        ImageGrid comeon =  new ImageGrid("./src/main/resources/"+ Name + "Map.png","./Output/" + Name + "Heatmap.png");
    }


    public static void main(String[] args) throws IOException, CsvException {

        HeatmapApp RSSI = new HeatmapApp(6,"RSSI");
        RSSI.CreateMap();


        HeatmapApp Throughput = new HeatmapApp(7,"Throughput");
        Throughput.CreateMap();





    }
}
