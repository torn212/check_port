package com.test.webdriver;

import com.test.webdriver.vo.ExcelVo;
import org.apache.poi.hssf.record.DVALRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestWebdriver {

    static List<WebDriver> list = new LinkedList<>();

    public static void main(String startTime, String endTime) throws Exception {
        for (int i = 0; i < 20; i++) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriver dr = new FirefoxDriver(options);
            list.add(dr);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("start=====================" + System.currentTimeMillis());

//        System.setProperty("webdriver.gecko.driver", "D:\\code\\dubbo\\learn\\webdriver\\geckodriver.exe");  //调用本地driver
        FirefoxOptions options = new FirefoxOptions();
        WebDriver dr = new FirefoxDriver(options);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(startTime);

        boolean flag = true;
        while (flag) {
            try {
                String dateStr = sdf.format(date);
                dr.get("https://www.landchina.com/default.aspx?tabid=263");
                TimeUnit.MILLISECONDS.sleep(2000 + new Random().nextInt(1000));
                //搜索条件
                bulidSearch(dr, dateStr);
                //获取所有详情页面的url
                List<String> urls = gainAllUrl(dr, dateStr);
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        //生成文件
                        packageData(dr, urls, dateStr + ".xlsx");
                    }
                });

                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, 1);
                date = c.getTime();

                Calendar end = Calendar.getInstance();
                end.setTimeInMillis(sdf.parse(endTime).getTime());
                end.set(Calendar.HOUR_OF_DAY, 23);
                end.set(Calendar.MINUTE, 59);
                end.set(Calendar.SECOND, 59);
                if (!c.before(end)) {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("网站被封" + e.getMessage());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                continue;
            }
        }
        System.out.println("end=====================" + System.currentTimeMillis());
    }


    //组装查询条件
    private static void bulidSearch(WebDriver dr, String key) {
        WebElement checkboxElement = dr.findElement(By.id("TAB_QueryConditionItem270"));
        checkboxElement.click();
        WebElement startDateElement = dr.findElement(By.id("TAB_queryDateItem_270_1"));
        startDateElement.sendKeys(key);
        WebElement endDateElement = dr.findElement(By.id("TAB_queryDateItem_270_2"));
        endDateElement.sendKeys(key);
        WebElement queryButtonElement = dr.findElement(By.id("TAB_QueryButtonControl"));
        queryButtonElement.click();
        try {
            TimeUnit.MILLISECONDS.sleep(500 + new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //获取所有详情页 url
    private static List<String> gainAllUrl(WebDriver dr, String dateStr) {
        List<String> urls = new ArrayList();
        int currentPage = 1;
        while (true) {
            try {
                WebElement tableElement = dr.findElement(By.id("TAB_contentTable"));
                List<WebElement> trList = tableElement.findElements(By.tagName("tr"));
                for (int i = 1; i < trList.size(); i++) {
                    List<WebElement> tdList = trList.get(i).findElements(By.tagName("td"));
                    String url = tdList.get(2).findElement(By.tagName("a")).getAttribute("href");
                    if (!urls.contains(url)) {
                        urls.add(url);
                    }
                }
                List<WebElement> elements = dr.findElements(By.cssSelector("td.pager>a"));
                List<WebElement> elementsInput = dr.findElements(By.cssSelector("td.pager>input"));

                if (elementsInput.size() == 2) {
                    currentPage = Integer.valueOf(elementsInput.get(0).getAttribute("value"));
                }

                WebElement next = null;
                for (WebElement element : elements) {
                    if (element.getText().equals("下页")) {
                        next = element;
                    }
                }
                if (next != null) {
                    try {
                        String attr = next.getAttribute("href");
                        if (null == attr || attr.length() < 1) {
                            System.out.println("最后一页");
                            break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println("最后一页" + e.getMessage());
                        break;
                    }
                    next.click();
                    try {
                        TimeUnit.MILLISECONDS.sleep(300 + new Random().nextInt(300));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return urls;
                }
            } catch (Exception e) {
                System.out.println("网站被封" + e.getMessage());
                try {
                    TimeUnit.SECONDS.sleep(2);
                    dr.get(dr.getCurrentUrl());
                    TimeUnit.SECONDS.sleep(4);
                    bulidSearch(dr, dateStr);
                    TimeUnit.SECONDS.sleep(4);
                    List<WebElement> elementsInput = dr.findElements(By.cssSelector("td.pager>input"));
                    if (elementsInput.size() == 2) {
                        elementsInput.get(0).clear();
                        elementsInput.get(0).sendKeys(currentPage + "");
                        elementsInput.get(1).click();
                        TimeUnit.SECONDS.sleep(4);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                continue;
            }
        }
        return urls;
    }

    //生成文件
    private static void packageData(WebDriver dr, List<String> urls, String fileName) {
        List<ExcelVo> excelVos = new ArrayList();
        for (int i = 0; i < urls.size(); i++) {
            ExcelVo excelVo = new ExcelVo();
            dr.get(urls.get(i));
            try {
                TimeUnit.MILLISECONDS.sleep(500 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                excelVo.setDistrict(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r1_c2_ctrl")).getText());
                excelVo.setEsn(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r1_c4_ctrl")).getText());
                excelVo.setProjectName(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r17_c2_ctrl")).getText());
                excelVo.setLLocDesc(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r16_c2_ctrl")).getText());
                excelVo.setLArea(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r2_c2_ctrl")).getText());
                excelVo.setLSource(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r2_c4_ctrl")).getText());
                excelVo.setLUseType(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r3_c2_ctrl")).getText());
                excelVo.setLSupplyType(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r3_c4_ctrl")).getText());
                excelVo.setLUseYears(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r19_c2_ctrl")).getText());
                excelVo.setIndustryType(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r19_c4_ctrl")).getText());
                excelVo.setLGrade(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r20_c2_ctrl")).getText());
                excelVo.setFinalPrice(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r20_c4_ctrl")).getText());
                excelVo.setLandUser(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r9_c2_ctrl")).getText());
                excelVo.setLCapacityRateL(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f2_r1_c2_ctrl")).getText());
                excelVo.setLCapacityRateH(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f2_r1_c4_ctrl")).getText());
                excelVo.setLDeliverDate(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r21_c4_ctrl")).getText());
                excelVo.setPlannedDateS(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r22_c2_ctrl")).getText());
                excelVo.setPlannedDateE(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r22_c4_ctrl")).getText());
                excelVo.setConstructionDateS(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r10_c2_ctrl")).getText());
                excelVo.setConstructionDateE(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r10_c4_ctrl")).getText());
                excelVo.setSeller(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r14_c2_ctrl")).getText());
                excelVo.setContractDate(dr.findElement(By.id("mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r14_c4_ctrl")).getText());
                excelVo.setUrl(urls.get(i));
                excelVos.add(excelVo);
            } catch (Exception e) {
                System.out.println("网站被封" + e.getMessage());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                i--;
                continue;
            }
        }
        try {
            ExcelUtils.writeExcel(fileName, "d:\\土地网站数据\\", excelVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
