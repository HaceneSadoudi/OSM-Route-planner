package com.night.osmrouteplanner.graphics;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Pencil {

    private GraphicsContext gc;
    //Vector to store the coordinates of Buildings
    private Vector<double[]> coordinatesLongitudeBuildings;
    private Vector<double[]> coordinatesLatitudeBuildings;

    //Vector to store the coordinates of Waters Ways
    private Vector<double[]> coordinatesLongitudeWaters;
    private Vector<double[]> coordinatesLatitudeWaters;

    //Vector to store the coordinates of Waters Relations
    private Vector<double[]> coordinatesRelationsLongitudeWaters;
    private Vector<double[]> coordinatesRelationsLatitudeWaters;

    //Vector to store the coordinates of Parks
    private Vector<double[]> coordinatesLongitudeParks;
    private Vector<double[]> coordinatesLatitudeParks;

    //Vector to store the coordinates of Roads
    private Vector<double[]> coordinatesLongitudeRoads;
    private Vector<double[]> coordinatesLatitudeRoads;

    //Vector to store the coordinates of Primary Roads
    private Vector<double[]> coordinatesPrimaryLongitudeRoads;
    private Vector<double[]> coordinatesPrimaryLatitudeRoads;

    //Vector to store the coordinates of Secondary Roads
    private Vector<double[]> coordinatesSecondaryLongitudeRoads;
    private Vector<double[]> coordinatesSecondaryLatitudeRoads;

    //Vector to store the coordinates of Tertiary Roads
    private Vector<double[]> coordinatesTertiaryLongitudeRoads;
    private Vector<double[]> coordinatesTertiaryLatitudeRoads;

    //Vector to store the coordinates of Tertiary Roads
    private Vector<double[]> coordinatesTrunkLongitudeRoads;
    private Vector<double[]> coordinatesTrunkLatitudeRoads;

    //Vector to store the coordinates of Tertiary Roads
    private Vector<double[]> coordinatesMotorwayLongitudeRoads;
    private Vector<double[]> coordinatesMotorwayLatitudeRoads;


    int zoomLvl;
    int ladderX;
    int ladderY;
    public final int canvasWidth = 500;
    private final int canvasHeight = 500;
    private final double zoomMultiplier = 0.75;
    private final double zoomOffset = (1 / zoomMultiplier) - 1;
    private int[] arrayLadderX;
    private int[] arrayLadderY;


    private ArrayList<EventHandler> eventsList;

    Pencil(GraphicsContext gc) {
        this.gc = gc;
        eventsList = new ArrayList<>();
    }

    /**
     * Draw the buildings contour
     * @param lon       array of coordinates X
     * @param lat       array of coordinates Y
     */
    private void drawBuildingsLines(double[] lon, double[] lat) {
        if(zoomLvl > 5) {
            this.gc.setFill(Color.rgb(220,210,200,0.8));
            this.gc.setStroke(Color.rgb(100,100,100));
            this.gc.fillPolygon(lon, lat, lon.length);
            this.gc.strokePolygon(lon, lat, lon.length);
        }
    }

    /**
     * Draw the waters lines
     * @param lon       array of coordinates X
     * @param lat       array of coordinates Y
     */
    private void drawWatersLines(double[] lon, double[] lat) {
        if(zoomLvl > 5) this.gc.setLineWidth(2);
        this.gc.setStroke(Color.LIGHTBLUE);
        this.gc.strokePolygon(lon, lat, lon.length);
        this.gc.setLineWidth(1);
    }

    /**
     * Draw the waters fill
     * @param lon       array of coordinates X
     * @param lat       array of coordinates Y
     */
    private void drawWatersFill(double[] lon, double[] lat) {
        this.gc.setFill(Color.LIGHTBLUE);
        this.gc.fillPolygon(lon, lat, lon.length);
    }

    /**
     * Draw the parks contour
     * @param lon
     * @param lat
     */
    private void drawParksLines(double[] lon, double[] lat) {
        this.gc.setFill(Color.rgb(170, 210, 160));
        this.gc.fillPolygon(lon, lat, lon.length);
    }

    /**
     * Draw lines for roads with chosen color
     *
     * @param lon       array of coordinates X
     * @param lat       array of coordinates Y
     * @param color     chosen color
     */
    private void drawMainRoadsWithColor(double[] lon, double[] lat, Color color) {
        if(zoomLvl <= 5) this.gc.setLineWidth(3);
        else this.gc.setLineWidth(5);
        this.gc.setStroke(color);
        this.gc.strokePolygon(lon, lat, lon.length);
        this.gc.setLineWidth(1);

    }

    /**
     * Draw the primary roads
     *
     * @param lon       array with coordinate X
     * @param lat       array with coordinate Y
     */
    private void drawPrimaryRoadsLines(double[] lon, double[] lat) {
        drawMainRoadsWithColor(lon, lat, Color.rgb(253, 215,161));
    }

    /**
     * Draw the secondary roads
     *
     * @param lon
     * @param lat
     */
    private void drawSecondaryRoadsLines(double[] lon, double[] lat) {
       drawMainRoadsWithColor(lon , lat, Color.rgb(246, 250, 187));
    }

    /**
     * Draw the tertiary roads
     *
     * @param lon array of coordinates X
     * @param lat array of coordinates Y
     */
    private void drawTertiaryRoadsLines(double[] lon, double[] lat) {
        drawMainRoadsWithColor(lon, lat, Color.rgb(255, 255, 255));
    }
}
