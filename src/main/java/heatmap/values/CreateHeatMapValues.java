package heatmap.values;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math3.geometry.spherical.twod.Edge;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CreateHeatMapValues {

//        private Map<int[],Double> Values;
        private double[][]  Values;
        private Map<String , Point2D.Double> EdgeCoordinates;
        double[][] values;
        private File inputFile;
        private List<String[]> csvBody;
        private int nofrows;
        private Point Grid;

        public CreateHeatMapValues(String csvfile,Point2D.Double maxcor, Point2D.Double mincor, Point _Grid) throws IOException, CsvException {


            Grid = _Grid;

            EdgeCoordinates = new HashMap<String, Point2D.Double>();
            EdgeCoordinates.put("maxcor",maxcor);
            EdgeCoordinates.put("mincor",mincor);

            Values = new double[4][10];
            inputFile = new File(csvfile);
            this.Readfile();
        }

        private void Readfile()throws IOException, CsvException {

            CSVReader reader = new CSVReader(new FileReader(this.inputFile));
            this.csvBody = reader.readAll();
            System.out.println(this.csvBody.size());
            this.nofrows=(this.csvBody.size());
            reader.close();
        }

        private Point CalculateGridCell(Point2D.Double Coordinates){
            if (Coordinates.getX() <= EdgeCoordinates.get("maxcor").getX() && Coordinates.getY() <= EdgeCoordinates.get("maxcor").getY()
                    && Coordinates.getX() >= EdgeCoordinates.get("mincor").getX() && Coordinates.getY() >= EdgeCoordinates.get("mincor").getY()) {

                double differenceY = EdgeCoordinates.get("maxcor").getY() - EdgeCoordinates.get("mincor").getY();
                double differenceX = EdgeCoordinates.get("maxcor").getX() - EdgeCoordinates.get("mincor").getX();

                System.out.println(differenceX);
                System.out.println(differenceY);
//            System.out.println((Coordinates.getY()));

                int savefromunderflow = 1000;

                double Xpercell = savefromunderflow * differenceX /  Grid.getX();
                double Ypercell = savefromunderflow * differenceY / Grid.getY();


                double Xdistance = savefromunderflow * Coordinates.getX() - savefromunderflow * EdgeCoordinates.get("mincor").getX();
                double Ydistance = savefromunderflow * Coordinates.getY() - savefromunderflow * EdgeCoordinates.get("mincor").getY();


                System.out.println(Xpercell);
                System.out.println(Ypercell);
//
                System.out.println(Xdistance);
                System.out.println(Ydistance);


                double latpercel = Xdistance / Xpercell;
                double longpercel = Ydistance / Ypercell;

                System.out.println(latpercel);
                System.out.println(longpercel);

                return new Point((int) latpercel, (int) longpercel);
            }
            return null;
        }



        private void FillZ(int rssicolumn, int latcolumn, int longcolumn) {
            int size = csvBody.size();
            for (int row = 2; row < size; row++) {
//                int[] indeces = {row, column};

//              System.out.println(csvBody.get(row)[latcolumn]);
//              System.out.println(csvBody.get(row)[longcolumn]);


                    Point temp;
                    if ( (temp = CalculateGridCell(new Point2D.Double(Double.parseDouble(csvBody.get(row)[latcolumn]), Double.parseDouble(csvBody.get(row)[longcolumn] ) ) ) ) !=null)
                        System.out.println("Row=" + row + " xcell=" + (temp.getX() +  1) + " || ycell=" + (temp.getY() +  1) );



            }
//
        }

    public static void main(String[] args) throws IOException, CsvException {
        CreateHeatMapValues Val = new CreateHeatMapValues("Output/all_vehicles.csv",new Point2D.Double(23.77539,37.9686200), new Point2D.Double(23.7647600,37.9668800),new Point(10,4));

        Val.FillZ(4,2,3);

    }
}
