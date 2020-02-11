package com.example.checkport;

import com.test.webdriver.TestWebdriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PropertyKey;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CheckPortApplication implements CommandLineRunner {

    @Value("${start_time}")
    private String startTime;
    @Value("${end_time}")
    private String endTime;

    public static void main(String[] args) {
        SpringApplication.run(CheckPortApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            TestWebdriver.main(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
