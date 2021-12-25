package com.night.osmrouteplanner;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class OSMData {
    /** Overpass API endpoint */
    private static final String API_ENDPOINT = "https://overpass-api.de/api/interpreter";

    /** Directory where xml files will be stored */
    public static final String DIRNAME = "OSMData";



    /**
     * Create city directory where XML files will be sotred.
     * @param   city    Name of the city
     */
    public static void mkCityDir(String city) {
        File dir = new File(DIRNAME+File.separator+city);
        if(!dir.exists()) dir.mkdirs();
    }

}
