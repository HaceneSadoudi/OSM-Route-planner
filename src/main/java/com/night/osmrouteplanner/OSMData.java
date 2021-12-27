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

}
