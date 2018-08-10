package com.teamloyaltycustomer.ontime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnTimeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnTimeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String[] turno = {"M", "M", "M", "R", "N", "N", "N", "L", "R", "R", "P", "P", "P", "R", "R"};
//        if (args.length == 0)
//            return;

        for (int i = 9; i < 13; i++) {
            System.out.println("Starting count with " + i + " element.");
            CustomShiftCalculator calculator = new CustomShiftCalculator(i);
        }
    }

}
