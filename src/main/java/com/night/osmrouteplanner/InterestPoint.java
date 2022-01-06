package com.night.osmrouteplanner;

import com.night.osmrouteplanner.graphics.Coordinates;

public class InterestPoint {

    private String name;
    private String type;
    private Coordinates unconvertedCoordinates;
    private Coordinates coordinates;

    public InterestPoint(String parsedName, String parsedType, double parsedLon, double parsedLat) {
        name = parsedName;
        coordinates = new Coordinates(parsedLon,parsedLat);
        type = parsedType;

    }

    /**
     * Get the name of the interest point
     * @return the name of the interest point
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of the interest point
     * @return the type of the interest point
     */
    public String getType(){
        return type;
    }

    /**
     * Get the coordinates of the interest point
     * @return the coordinates of the interest point
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Get the unconverted coordinates of the interest point
     * @return the Unconverted coordinates of the interest point
     */
    public Coordinates getUnconvertedCoordinates() {
        return unconvertedCoordinates;
    }

    /**
     * Set the  the unconverted coordinates of the interest point
     * @param lon the longitude coordinate
     * @param lat the latitude coordinate
     */
    public void setUnconvertedCoodinates(double lon, double lat) {
        unconvertedCoordinates = new Coordinates(lon,lat);
    }
}
