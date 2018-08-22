package com.teamloyaltycustomer.ontime;

import java.util.ArrayList;

public class Shift extends ArrayList<String> {

    private static final String BASE_SHIFT = "MMMRNNNLRRPPRR";
    private String[] lines;
    private Integer[] pointer;
    private Integer size;

    private Shift() {

    }

    public Shift(Integer size) {
        size--;
        this.size = size;

        pointer = new Integer[size];

        populateLines(size);
        for (int i = 0; i < size; i++) {
            this.add(i, lines[0]);
        }
    }

    private void populateLines(Integer size) {
        lines = new String[size];
        lines[0] = BASE_SHIFT; 
        for (int i = 1; i < size; i++) {
            lines[i] = lines[i-1].substring(1).concat(lines[i-1].substring(0, 1));
        }

    }

}
