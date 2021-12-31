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
     * @param lon
     * @param lat
     */
    private void drawBuildingsLines(double[] lon, double[] lat) {
        if(zoomLvl > 5) {
            this.gc.setFill(Color.rgb(220,210,200,0.8));
            this.gc.setStroke(Color.rgb(100,100,100));
            this.gc.fillPolygon(lon, lat, lon.length);
            this.gc.strokePolygon(lon, lat, lon.length);
        }
    }
}
