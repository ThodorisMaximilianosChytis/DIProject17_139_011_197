package heatmap.graphics;


import org.tc33.jheatchart.HeatChart;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class heatmap {
    public static void main(String[] args){
        double[][] data = new double[][]{
                {1,1.5,2,2.5,3,3.5,4,4.5,5,5.5},
                {0,0,0,0,0,0,0,0,0,0},
                {1,2,3,4,5,6,7,1,2,3},
                {1,2,3,4,5,6,7,1,2,3}
        };

        HeatChart map = new HeatChart(data);
        map.setColourScale(1.0);
        map.setHighValueColour(Color.GREEN);
        map.setLowValueColour(Color.RED);
        map.setChartMargin(0);
        map.setCellSize(new Dimension(133,68));
        map.setAxisThickness(0);
        map.setShowXAxisValues(false);
        map.setShowYAxisValues(false);
        map.setBackgroundColour(Color.CYAN);


        try {
            map.saveToFile(new File("./src/main/resources/java-heat-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
