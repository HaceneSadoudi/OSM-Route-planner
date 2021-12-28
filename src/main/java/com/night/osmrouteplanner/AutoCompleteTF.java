package com.night.osmrouteplanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.*;

public class AutoCompleteTF extends TextField {


    private SortedSet<String> entries;
    private ContextMenu contextMenuPopup;
    private final static int MAX_ITEMS = 10;

    public AutoCompleteTF() {
        super();
        entries = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                Collator c = Collator.getInstance(Locale.FRANCE);
                c.setStrength(Collator.PRIMARY);
                return c.compare(s1, s2);
            }
        });
        contextMenuPopup = new ContextMenu();

    }


    /**
     * populate created items on the ContextMenu
     * @param searchRes     List of founded strings that matches the search
     */
    private void populateItems(List<String> searchRes) {
        List<CustomMenuItem> menuItems = new LinkedList<>();

        int count = Math.min(MAX_ITEMS, searchRes.size());
        for(int i=0;i<count;i++) {
            String result = searchRes.get(i);
            Label label = new Label(result);
            label.setPrefWidth(getPrefWidth() - 50);
            CustomMenuItem item = new CustomMenuItem(label);
            menuItems.add(item);
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setText(result);
                    contextMenuPopup.hide();
                }
            });
        }
        contextMenuPopup.getItems().clear();
        contextMenuPopup.getItems().addAll(menuItems);
    }


    /**
     * Read all cities from CSV file and insert them inside a SortedSet
     * @param file      CVS file
     */
    public void readCitiesFromCSV(File file) {
        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine().split(",")[0];
                entries.add(line);
            }
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }


}
