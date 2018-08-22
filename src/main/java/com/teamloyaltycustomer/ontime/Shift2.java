package com.teamloyaltycustomer.ontime;

import java.util.Arrays;

public class Shift2 {

    private static final String BASE_SHIFT = "MMMRNNNLRRPPRR";
    private String[] lines;
    Integer[] pointer;
    private Integer size;

    private Shift2() {

    }

    public Shift2(Integer size) {
        // size--;
        this.size = size;
        int i=0;

        pointer = new Integer[BASE_SHIFT.length()];
        Arrays.fill(pointer, 0);

        populateLines();
        while (true) {
            i++;
            calculateCoveredShifts();
            next();

            if (i % 10000000 == 0) {
                System.out.println("Worker " + size + " -> Processed " + String.format("%,d", i) + " permutations.");
                System.out.println(createMatrix());
            }
        }
    }

    private void next() {
        Integer cols = BASE_SHIFT.length();
        Integer rows = pointer.length;

        pointer[0]++;
        for (int i = 0; i < rows ; i++) {
            if (pointer[i] == cols && i < rows - 2) {
                pointer[i] = 0;
                pointer[i + 1]++;
            }
        }
        if (!(pointer[1] == 0 && pointer[2] == 0)) {
            if (pointer[1] > pointer[0])
                pointer[0] = pointer[1];
        }
    }

    private String getArray() {
        String retVal = new String();
        StringBuilder sb = new StringBuilder();
        for (int value : pointer) {
            sb.append(String.format("%02d", value));
            sb.append(";");
        }
        return sb.toString().substring(0, retVal.length() - 1);
    }

    private void populateLines() {
        lines = new String[BASE_SHIFT.length()];
        lines[0] = BASE_SHIFT;
        for (int i = 1; i < lines.length; i++) {
            lines[i] = lines[i - 1].substring(1).concat(lines[i - 1].substring(0, 1));
        }

    }

    private void calculateCoveredShifts() {

        Integer totColumns = BASE_SHIFT.length();
        Integer totRows = pointer.length;

        Integer totM = 0;
        Integer totP = 0;
        Integer totN = 0;
        Integer daysCovered = 0;

        for (int i = 0; i < totColumns; i++) {
            totM = 0;
            totP = 0;
            totN = 0;
            daysCovered = 0;
            for (int j = 0; j < totRows; j++) {

                if (lines[pointer[j]].substring(i, i + 1).equalsIgnoreCase("M"))
                    totM++;
                if (lines[pointer[j]].substring(i, i + 1).equalsIgnoreCase("P"))
                    totP++;
                if (lines[pointer[j]].substring(i, i + 1).equalsIgnoreCase("N"))
                    totN++;
            }
            if (totM >= 2 && totP >= 2 && totN >= 2) {
                daysCovered++;
            } else {
                return;
            }
        }
        if (daysCovered == totColumns) {
            // System.out.println("Total worker: " + totRows);
            Utils.printMatrix(createMatrix(), true);
        }
    }

    private String createMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            sb.append(lines[pointer[i]]);
            sb.append("\n");
        }
        return sb.toString();
    }

}
