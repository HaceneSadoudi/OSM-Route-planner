package com.night.osmrouteplanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private long[] wayId;

    private NodeList listWay;
    private NodeList listRelation;
    private NodeList listMember;

    private Map<Long, List<Double>> waysLatitude;
    private Map<Long, List<Double>> waysLongitude;


    Parser() {
        this.waysLatitude = new HashMap<>();
        this.waysLongitude = new HashMap<>();

    }
    void init(File data) {
        listWay = getNodesByType(data, "way");
        listRelation = getNodesByType(data,"relation");
        listMember = getNodesByType(data,"member");
        this.waysLatitude = new HashMap<>();
        this.waysLongitude = new HashMap<>();
    }

    public NodeList getNodesByType(File file, String nodeType) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName(nodeType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long[] getWayId() {
        return wayId;
    }

    public void setWayId(long[] wayId) {
        this.wayId = wayId;
    }

    public NodeList getListWay() {
        return listWay;
    }

    public void setListWay(NodeList listWay) {
        this.listWay = listWay;
    }

    public NodeList getListRelation() {
        return listRelation;
    }

    public void setListRelation(NodeList listRelation) {
        this.listRelation = listRelation;
    }

    public NodeList getListMember() {
        return listMember;
    }

    public void setListMember(NodeList listMember) {
        this.listMember = listMember;
    }

    public Map<Long, List<Double>> getWaysLatitude() {
        return waysLatitude;
    }

    public void setWaysLatitude(Map<Long, List<Double>> waysLatitude) {
        this.waysLatitude = waysLatitude;
    }

    public Map<Long, List<Double>> getWaysLongitude() {
        return waysLongitude;
    }

    public void setWaysLongitude(Map<Long, List<Double>> waysLongitude) {
        this.waysLongitude = waysLongitude;
    }

    public void parseParks(String city) {
        NodeList listWay = getNodesByType(new File(OSMData.DIRNAME+File.separator+city+File.separator+"Parks.xml"), "way");
        this.wayId = new long[listWay.getLength()];
        for(int i=0;i<listWay.getLength();i++) {
            Node node = listWay.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                List<Double> listLat = new ArrayList<>();
                List<Double> listLon = new ArrayList<>();
                Element element = (Element) node;
                long id = Long.parseLong(element.getAttribute("id"));
                this.wayId[i] = id;
                for(int j=0;j<element.getElementsByTagName("nd").getLength();j++) {
                    Node node1 = element.getElementsByTagName("nd").item(j);
                    Element element1 = (Element) node1;
                    listLat.add(Double.parseDouble(element1.getAttribute("lat")));
                    listLon.add(Double.parseDouble(element1.getAttribute("lon")));
                }
                waysLatitude.put(id, listLat);
                waysLongitude.put(id, listLon);
            }
        }
    }


}
