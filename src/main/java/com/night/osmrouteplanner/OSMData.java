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
    public static String sendRequest(String overpassQuery){
        String res = "";
        try {
            overpassQuery = URLEncoder.encode(overpassQuery, "UTF8");
            URL url = new URL(API_ENDPOINT+"?data="+overpassQuery);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream inputStream = con.getInputStream();
            Scanner s = new Scanner(inputStream);
            res = s.useDelimiter("\\A").next();
            inputStream.close();
            s.close();
        }catch(IOException e) {
            System.out.println(e);
        }
        return res;
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
            sc.close(); // Clean up Scanner
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
    public static void generateRoads(String city) {
        String query = "[out:xml];"
                + "("
                + "  way[\"highway\"]" + cityDelimitation + ";"
                + "  relation[\"highway\"]" + cityDelimitation + ";"
                + ");"
                + "out geom qt;";
        mkCityDir(city);
        stringToFile(sendRequest(query), DIRNAME+File.separator+city+File.separator+"Roads", ".xml");
    }
    /**
     * Generate Buildings.xml file containing a query result
     *
     * @param  city     Name of the city
     */
    public static void generateBuildings(String city) {
        String query = "[out:xml];"
                + "("
                + "  way[\"building\"]" + cityDelimitation + ";"
                + "  relation[\"building\"]" + cityDelimitation + ";"
                + ");"
                + "out geom qt;";
        mkCityDir(city);
        stringToFile(sendRequest(query), DIRNAME + File.separator + city + File.separator + "Buildings", ".xml");
    }

    /**
     * Generate Water.xml file containing a query result
     *
     * @param city      Name of the city
     */
    public static void generateWater(String city) {
        String query = "[out:xml];"
                        + "("
                        + "  way[\"natural\"~\"^water|bay|strait|coastline$\"]" + cityDelimitation + ";"
                        + "  way[\"landuse\"~\"^basin|reservoir$\"]" + cityDelimitation + ";"
                        + "  way[\"leisure\"~\"^swimming_pool$\"]" + cityDelimitation + ";"
                        + "  way[\"waterway\"]" + cityDelimitation + ";"
                        + "  relation[\"natural\"~\"^water|bay|strait|coastline$\"]" + cityDelimitation + ";"
                        + "  relation[\"landuse\"~\"^basin|reservoir$\"]" + cityDelimitation + ";"
                        + "  relation[\"leisure\"~\"^swimming_pool$\"]" + cityDelimitation + ";"
                        + "  relation[\"waterway\"]" + cityDelimitation + ";"
                        + ");"
                        + "out geom qt;";
        mkCityDir(city);
        stringToFile(sendRequest(query), DIRNAME + File.separator + city + File.separator + "Water", ".xml");
    }

    /**
     * Generate Water.xml file containing a query result
     *
     * @param city      Name of the city
     */
    public static void generateParks(String city) {
        String query =  "[out:xml];"
                        + "("
                        + "way[\"leisure\"~\"^(garden|park|golf_course|pitch|playground)$\"]" + cityDelimitation + ";"
                        + "relation[\"leisure\"~\"^(garden|park|golf_course|pitch|playground)$\"]" + cityDelimitation + ";"
                        + ");"
                        + "out geom qt;";
        mkCityDir(city);
        stringToFile(sendRequest(query), DIRNAME + File.separator + city + File.separator + "Parks", ".xml");
    }


    /**
     * Generate InterestingNodes.xml file containing a query result
     *
     * @param city      Name of the city
     */
    public static void generateInterestingNodes(String city) {
        String query =  "[out:xml];"
                        + "("
                        + " ("
                        + "     node[\"amenity\"~\"^hospital|casino|cinema|courthouse|place_of_worship|police|prison|townhall|library|school$\"]" + cityDelimitation + ";"
                        + "     way[\"amenity\"~\"^hospital|casino|cinema|courthouse|place_of_worship|police|prison|townhall|library|school$\"]" + cityDelimitation + ";"
                        + "     relation[\"amenity\"~\"^hospital|casino|cinema|courthouse|place_of_worship|police|prison|townhall|library|school$\"]" + cityDelimitation + ";"
                        + " );"
                        + " ("
                        + "     node[\"building\"~\"^civic|public|university$\"]" + cityDelimitation + ";"
                        + "     way[\"building\"~\"^civic|public|university$\"]" + cityDelimitation + ";"
                        + "     relation[\"building\"~\"^civic|public|university$\"]" + cityDelimitation + ";"
                        + " );"
                        + ");"
                        + "out geom qt;";
        mkCityDir(city);
        stringToFile(sendRequest(query), DIRNAME + File.separator + city + File.separator + "InterestingNodes", ".xml");
    }

    /**
     * Generate all xml files
     *
     * @param city      Name of the city
     */
    public static void generateAllXMLs(String city) {
        mkCityDir(city);
        generateRoads(city);
        generateBuildings(city);
        generateParks(city);
        generateWater(city);
        generateInterestingNodes(city);
    }

}


