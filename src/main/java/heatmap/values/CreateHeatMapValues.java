package heatmap.values;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CreateHeatMapValues {

//        private Map<int[],Double> Values;
        private CellInfo[][] Values;
        private double ValuesDatasum;
        private Map<String , Point2D.Double> EdgeCoordinates;
        private File inputFile;
        private List<String[]> csvBody;
        private int nofrows;
        private Point Grid;

        public CreateHeatMapValues(String csvfile,Point2D.Double maxcor, Point2D.Double mincor, Point _Grid) throws IOException, CsvException {


            Grid = _Grid;
            EdgeCoordinates = new HashMap<String, Point2D.Double>();
            EdgeCoordinates.put("maxcor",maxcor);
            EdgeCoordinates.put("mincor",mincor);

            Values = new CellInfo[(int) _Grid.getX()][(int) _Grid.getY()];

            inputFile = new File(csvfile);
            this.Readfile();
        }

        private void Readfile()throws IOException, CsvException {

            CSVReader reader = new CSVReader(new FileReader(this.inputFile));
            this.csvBody = reader.readAll();
//            System.out.println(this.csvBody.size());
            this.nofrows=(this.csvBody.size());
            reader.close();
        }

        private Point CalculateGridCell(Point2D.Double Coordinates){
            if (Coordinates.getX() <= EdgeCoordinates.get("maxcor").getX() && Coordinates.getY() <= EdgeCoordinates.get("maxcor").getY()
                    && Coordinates.getX() >= EdgeCoordinates.get("mincor").getX() && Coordinates.getY() >= EdgeCoordinates.get("mincor").getY()) {

                double differenceY = EdgeCoordinates.get("maxcor").getY() - EdgeCoordinates.get("mincor").getY();
                double differenceX = EdgeCoordinates.get("maxcor").getX() - EdgeCoordinates.get("mincor").getX();



                int savefromunderflow = 1000;

                double Xpercell = savefromunderflow * differenceX /  Grid.getX();
                double Ypercell = savefromunderflow * differenceY / Grid.getY();


                double Xdistance = savefromunderflow * Coordinates.getX() - savefromunderflow * EdgeCoordinates.get("mincor").getX();
                double Ydistance = savefromunderflow * Coordinates.getY() - savefromunderflow * EdgeCoordinates.get("mincor").getY();




                double latpercel = Xdistance / Xpercell;
                double longpercel = Ydistance / Ypercell;


                return new Point((int) latpercel, (int) longpercel);
            }
            return null;
        }



        public void FillZ(int datacolumn, int latcolumn, int longcolumn) {
            int size = csvBody.size();
            for (int row = 2; row < size; row++) {

                    Point temp;
                    Point2D.Double Coordinates = new Point2D.Double(Double.parseDouble(csvBody.get(row)[latcolumn]) , Double.parseDouble(csvBody.get(row)[longcolumn] ) ) ;
                    if ( (temp = CalculateGridCell( Coordinates ) )!=null) {


                        if (Values[(int) temp.getX()][(int) temp.getY()]==null){
                            Values[(int) temp.getX()][(int) temp.getY()] = new CellInfo();
                        }
                        Values[(int) temp.getX()][(int) temp.getY()].addVisitor();
                        Values[(int) temp.getX()][(int) temp.getY()].add2Data( Double.parseDouble(csvBody.get(row)[datacolumn]) );
                        ValuesDatasum+= Double.parseDouble(csvBody.get(row)[datacolumn]);

                    }
            }

        }
        public void Zvalperc(double[][] HeatmapData){

            for (int i=0; i<Grid.getX(); i++){
                for(int j=0; j<Grid.getY() ; j++) {
                    if (Values[i][(int) Grid.getY()-1-j] != null) {
                        HeatmapData[j][i] = 100 * Values[i][(int) Grid.getY()-1-j].getDATASUM() / ValuesDatasum;
                    }else{
                        HeatmapData[j][i]=0;
                    }
//                    System.out.println(HeatmapData[j][i]);
                }
            }


        }


        public void ValPrintZ() {
            for (int i=0; i<Grid.getX(); i++){
                for(int j=0; j<Grid.getY() ; j++) {
                    if (Values[i][j] != null){
                        System.out.print(i + "" + j);
                        Values[i][j].Print();
                        System.out.println(Values[i][j].getMeanData());

                    }
                }
            }
        }

}
