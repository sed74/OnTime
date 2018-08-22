package com.teamloyaltycustomer.ontime;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    private Utils() {}
    
    public static void printMatrix(String stringToPrint) {
        printMatrix(stringToPrint, false);
    }
        public static void printMatrix(String stringToPrint, Boolean printToFile) {
 
        if (printToFile) {
            BufferedWriter writer;
            try {

                writer = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "\\Desktop\\file.txt", true));
                writer.append(stringToPrint + "\n");
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            System.out.println(stringToPrint);
        }

    }

}
