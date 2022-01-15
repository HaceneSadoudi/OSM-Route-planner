package com.night.osmrouteplanner;

public class Converter {

    private double[] arrayOfLon;
    private double[] arrayOfLat;


    public Converter(double[] parserArrayLon, double[] parserArrayLat) {
        arrayOfLon = parserArrayLon;
        arrayOfLat = parserArrayLat;
    }


    public double[] convertLon(double minLon) {
        int nbrOfPoints = arrayOfLon.length;
        double[] lonX = new double[nbrOfPoints];

        for(int i=0;i<nbrOfPoints;i++) {
            double minLonPixel = 500 * ((minLon + 180) / 360);
            double xPixel = 500 * ((arrayOfLat[i] + 180) / 360);
        }
        return lonX;
    }
}
