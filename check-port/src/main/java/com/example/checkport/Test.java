package com.example.checkport;

import com.example.checkport.pachong.GetContent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.util.StringUtils;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://www.landchina.com/default.aspx?tabid=261&wmguid=20aae8dc-4a0c-4af5-aedf-cc153eb6efdf&p=");
            Thread.sleep(6000);
            for (int i = 0; i < 100; i++) {
                List<WebElement> es = driver.findElements(By.cssSelector("table#TAB_contentTable > tbody > tr > td.queryCellBordy > a"));
                for (WebElement e : es) {
                    String href = e.getAttribute("href");
                    if (!StringUtils.isEmpty(href)) {
                        System.out.println(href);
                        GetContent.add(href);
                    }
                }
                List<WebElement> elements = driver.findElements(By.cssSelector("td.pager>a"));
                WebElement next = null;
                for (WebElement element : elements) {
                    if (element.getText().equals("下页")) {
                        next = element;
                    }
                }
                if (next != null) {
                    next.click();
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
