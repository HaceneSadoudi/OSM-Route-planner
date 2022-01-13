package com.night.osmrouteplanner;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private NodeList listWay;
    private NodeList listRelation;
    private NodeList listMember;

    private Map<Long, List<Double>> waysLatitude;
    private Map<Long, List<Double>> waysLongitude;


    void init(File data) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(data);
            doc.getDocumentElement().normalize();
            listWay = doc.getElementsByTagName("way");
            listRelation = doc.getElementsByTagName("relation");
            listMember = doc.getElementsByTagName("member");
            this.waysLatitude = new HashMap<>();
            this.waysLongitude = new HashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
