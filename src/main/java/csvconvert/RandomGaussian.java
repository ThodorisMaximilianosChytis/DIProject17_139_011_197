package csvconvert;



import java.util.Random;

import static java.lang.Math.sqrt;


public class RandomGaussian {

    public double MEAN;
    public double VARIANCE;

    public RandomGaussian(double mean,double variance){
        MEAN = mean;
        VARIANCE = variance;

    }
    private Random fRandom = new Random();

    public double getGaussian(){
        return MEAN + fRandom.nextGaussian() * sqrt(VARIANCE);
    }

    public double getMEAN() {
        return MEAN;
    }

    public double getVARIANCE() {
        return VARIANCE;
    }

    public void setMEAN(double MEAN) {
        this.MEAN = MEAN;
    }

    public void setVARIANCE(double VARIANCE) {
        this.VARIANCE = VARIANCE;
    }
}