//package form;
//
//import java.util.*;
//
//public class Formula {
//
//    private long int R = 6371 * (10 ^ 6);
//    public double late;
//    public double longe;
//
//    public Formula ( double latstart, double longstart, double angle, double speed ) {
//
//        long double d = speed/R;
//        double latstartradian = Math.toRadians(latstart);
//        double longstartradian = Math.toRadians(longstart);
//
//        double latendradian = Math.asin(Math.sin(latstartradian) * Math.cos(d) + Math.cos(latstartradian) * Math.sin(d) * Math.cos(angle));
//        double longendradian = longstartradian + Math.atan2(Math.sin(angle) * Math.sin(d) * Math.cos(latstartradian), Math.cos(d) - Math.sin(latstartradian) * Math.sin(latendradian));
//
//        this.late = Math.toDegrees(latendradian);
//        this.longe = Math.toDegrees(longendradian);
//
//
//    }
//
//    public double getlatend() {
//        return late;
//    }
//
//    public double getlongend() {
//        return longe;
//    }
//
//
////    public Formula formula ( double latstart, double longstart, double angle, double speed ) {
////        long double d = speed/R;
////        double latstartradian = Math.toRadians(latstart);
////        double longstartradian = Math.toRadians(longstart);
////
////        double latendradian = Math.asin(Math.sin(latstartradian) * Math.cos(d) + Math.cos(latstartradian) * Math.sin(d) * Math.cos(angle));
////        double longendradian = longstartradian + Math.atan2(Math.sin(angle) * Math.sin(d) * Math.cos(latstartradian), Math.cos(d) - Math.sin(latstartradian) * Math.sin(latendradian));
////
////        return new Formula(Math.toDegrees(latendradian),Math.toDegrees(longendradian));
////    }
//}