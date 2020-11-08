package heatmap.values;

public class CellInfo {
    private int visitor;
    private double DATASUM;

    public CellInfo(){
        visitor=0;
        DATASUM=0;
    }

    public void Print(){
        System.out.println("Visitors are " + visitor);
        System.out.println("DATASUM is " + DATASUM);
    }

    public void addVisitor(){
        visitor++;
    }

    public void addVisitor(int inc){
        visitor+=inc;
    }

    public void add2Data(double increment){
        DATASUM+= increment;
    }

    public double getDATASUM() {
        return DATASUM;
    }

    public int getVisitor() {
        return visitor;
    }

}
