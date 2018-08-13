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
        if (args.length == 0)
            return;

        for (int i = 10; i < 13; i++) {
            System.out.println("Starting count with " + i + " elements.");
            CustomShiftCalculator calculator = new CustomShiftCalculator(i);
        }
    }

}
