package com.night.osmrouteplanner.graphics;

public class Coordinates {

    private double lon;
    private double lat;

    Coordinates(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    protected double getLon() {
        return lon;
    }

    protected double getLat() {
        return lat;
    }

    protected void setLon(double lon) {
        this.lon = lon;
    }

    protected void setLat(double lat) {
        this.lat = lat;
    }
}
