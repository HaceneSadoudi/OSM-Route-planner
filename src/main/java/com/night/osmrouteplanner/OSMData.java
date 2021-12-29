package com.night.osmrouteplanner;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class OSMData {
    /** Overpass API endpoint */
    private static final String API_ENDPOINT = "https://overpass-api.de/api/interpreter";

    /** Directory where xml files will be stored */
    public static final String DIRNAME = "OSMData";

    public static double cityNorthLimit;
    public static double cityEastLimit;
    public static double citySouthLimit;
    public static double cityWestLimit;
    public static String cityDelimitation;

    public final static double AREA_RANG = 0.05;

    /**
     * Send a http request to Overpass API then return the response
     * @param  overpassQuery      Overpass query
     * @throws IOException
     * @return http response as a String
     */
    public static String sendRequest(String overpassQuery) throws IOException {
        overpassQuery = URLEncoder.encode(overpassQuery, "UTF8");
        URL url = new URL(API_ENDPOINT+"?data="+overpassQuery);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        InputStream inputStream = con.getInputStream();
        Scanner s = new Scanner(inputStream);
        return s.useDelimiter("\\A").next();
    }


    /**
     * Create city directory where XML files will be sotred.
     * @param   city    Name of the city
     */
    public static void mkCityDir(String city) {
        File dir = new File(DIRNAME+File.separator+city);
        if(!dir.exists()) dir.mkdirs();
    }


    /**
     * Create a new file then write the given string content to it
     *
     * @param   content     a given string to write to a file
     * @param   filename    the name of the created file
     * @param   ext         the file extension
     */
    public static void stringToFile(String content, String filename, String ext) {
        try {
            File file = new File(filename + ext);
            file.createNewFile();
            BufferedWriter bwr = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bwr.write(content);
            bwr.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Get the city coordinates from the csv file
     *
     * @param city      name of the city that the user chooses
     */
    public static void getCityCoordinates(String city) {
        double lat = 0, lon = 0;
        try {
            File file  = new File("fr.csv");
            Scanner sc = new Scanner(file, "UTF-8");
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                if(line[0].contains(city)) {
                    lat = Double.parseDouble(line[1]);
                    lon = Double.parseDouble(line[2]);
                    break;
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
        cityNorthLimit = lat + AREA_RANG;
        citySouthLimit = lat - AREA_RANG;
        cityEastLimit  = lon + AREA_RANG;
        cityWestLimit  = lon - AREA_RANG;

        cityDelimitation = "(" +
                                citySouthLimit + ", " +
                                cityWestLimit + ", " +
                                cityNorthLimit + ", " +
                                cityEastLimit +
                            ")";
    }

    /**
     * Get the city limits (latitude - longitude)
     *
     * @param city      Name of the city
     * @return          Array with S-W-N-E limits
     */
    public static double[] getCityLimits(String city) {
        // get the city coordinates
        getCityCoordinates(city);
        // Create the directory in which XML files will be stored
        mkCityDir(city);

        double[] cityLimits = {
                                citySouthLimit,
                                cityWestLimit,
                                cityNorthLimit,
                                cityEastLimit
                              };
        return cityLimits;
    }



    /**
     * Generate Road.xml file containing a query result
     *
     * @param  city     Name of the city
     */
    public static void generateRoads(String city) throws IOException {
        String query = "[out:xml];"
                + "("
                + "  way[\"highway\"]" + cityDelimitation + ";"
                + "  relation[\"highway\"]" + cityDelimitation + ";"
                + ");"
                + "out geom qt;";
        System.out.println(query);
        mkCityDir(city);
        stringToFile(sendRequest(query), DIRNAME+File.separator+city+File.separator+"Roads", ".xml");
    }
}
