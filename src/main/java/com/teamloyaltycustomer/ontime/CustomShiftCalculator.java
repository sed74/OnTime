package com.teamloyaltycustomer.ontime;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CustomShiftCalculator {

    private static final String[] BASE_SHIFT = {"M", "M", "M", "R", "N", "N", "N", "L", "R", "R", "P", "P", "P", "R", "R"};
    private String[][] shift = null;


    public CustomShiftCalculator(Integer matrixSize) {
        super();
        shift = new String[matrixSize][BASE_SHIFT.length];
        populateMatrix(matrixSize);
        permute();
    }

    private void permute() {
        Integer cols = shift[0].length;
        Integer rows = shift.length;
        Integer[] pointer = new Integer[rows];
        Long i = 0l;
        Double tot = java.lang.Math.pow(cols, rows);

        Arrays.fill(pointer, 0);

        try {
            while (pointer[rows - 1] < cols) {
                i++;
                shift[0] = shiftRow(shift[0]);
                calculateCoveredShifts();
                
                pointer[0]++;
                for (int j = 0; j < pointer.length; j++) {
                    if (pointer[j] == cols - 1 && j < (rows - 1)) {
                        shift[j + 1] = shiftRow(shift[j + 1]);
                        pointer[j + 1]++;
                        pointer[j] = 0;
                    }
                }
                if (i % 10000000 == 0) {
                    // System.out.println("Worker " + rows + " -> " + String.format("%,d", i) + " of " +
                    // String.format("%,.0f", tot)); 
                    System.out.println("Worker " + rows + " -> " + String.format("%,.5f", ((i / tot) * 100)) + "%. Processed "
                            + String.format("%,d", i) + " permutations.");

                }
            }
        } catch (Exception e) {
            printArray(pointer);
            e.printStackTrace();;
        }

    }

    private void populateMatrix(Integer matrixSize) {

        for (int i = 0; i < matrixSize; i++) {
            shift[i] = BASE_SHIFT;
        }
    }

    private String[] shiftRow(String[] row) {
        String[] retVal = new String[row.length];

        retVal[0] = row[row.length - 1];
        for (int i = 0; i < row.length - 1; i++) {
            retVal[i + 1] = row[i];
        }
        return retVal;
    }

    private void printArray(Integer[] array) {
        String retVal = "";
        for (int i = 0; i < array.length; i++) {
            retVal += String.format("%02d", array[i]) + ",";
        }
        System.out.println(retVal.substring(0, retVal.length() - 1));
    }

    private void calculateCoveredShifts() {

        Integer totColumns = shift[0].length;
        Integer totRows = shift.length;

        Integer totM = 0, totP = 0, totN = 0;
        Integer daysCovered = 0;

        for (int i = 0; i < totColumns; i++) {
            totM = 0;
            totP = 0;
            totN = 0;
            daysCovered = 0;
            for (int j = 0; j < totRows; j++) {
                // System.out.println(String.format("Checking cell %s,%s", Integer.toString(i),
                // Integer.toString(j)));

                if (shift[j][i].equalsIgnoreCase("M"))
                    totM++;
                if (shift[j][i].equalsIgnoreCase("P"))
                    totP++;
                if (shift[j][i].equalsIgnoreCase("N"))
                    totN++;

                // System.out.println(String.format("M: %s - P: %s - N: %s", totM, totP, totN));
            }
            if (totM >= 2 && totP >= 2 && totN >= 2) {
                daysCovered++;
            } else {
                return;
            }
        }
        if (daysCovered == totColumns) {
            // System.out.println("Total worker: " + totRows);
            printMatrix(true);
        }
    }

    private void printMatrix(Boolean printToFilw) {
        String row = "";

        for (int i = 0; i < shift.length; i++) {
            for (int j = 0; j < shift[i].length; j++) {
                row += shift[i][j].toString() + ",";
            }
            row = row.substring(0, row.length() - 1) + "\n";
        }
        if (printToFilw) {
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\F872D~1.MAR\\AppData\\Local\\Temp\\file.txt", true));
                writer.append(row + "\n");
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            System.out.println(row);
        }

    }


}
