package com.night.osmrouteplanner;

import com.night.osmrouteplanner.graphics.Coordinates;

import java.util.ArrayList;

public class RoadName {

    String name;
    double angle;
    ArrayList<Coordinates> coordList;

    public RoadName(ArrayList<Coordinates> parsedCoordList, String parsedName) {
        name = parsedName;
        coordList = parsedCoordList;
    }
}
