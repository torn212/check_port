package com.example.checkport.pachong;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class GetContent {
    public static final ConcurrentLinkedQueue<String> hrefs = new ConcurrentLinkedQueue<>();
    public static final WebDriver driver = new FirefoxDriver();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    public static void init() {
        while (true) {
            try {
                if (hrefs.isEmpty()) {
                    TimeUnit.SECONDS.sleep(1);
                }
                while (!hrefs.isEmpty()) {
                    String poll = hrefs.poll();
                    if (!StringUtils.isEmpty(poll)) {
                        driver.get(poll);
                        Thread.sleep(1500 + new Random().nextInt(1000));
                        WebElement lblTitle = driver.findElement(By.id("lblTitle"));


                        System.out.println(lblTitle.getText());
                        if (lblTitle != null) {
                            List<WebElement> elements = driver.findElements(By.xpath("//table[@border='1']"));
                            for (WebElement element : elements) {
                                System.out.println(element.getAttribute("innerHTML"));
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    public static void add(String href) {
        hrefs.add(href);
    }
}
