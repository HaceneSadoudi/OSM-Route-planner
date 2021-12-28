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



}
