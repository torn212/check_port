package com.test.webdriver;

import com.test.webdriver.vo.ExcelVo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtils {

    public static void writeExcel(String fileName, String filePath, List<ExcelVo> list) throws Exception {
        OutputStream outputStream  = new FileOutputStream(filePath+fileName);
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet xssfSheet = xssfWorkbook.createSheet();
        writeTitle(xssfSheet);
        for (int i = 0; i < list.size(); i++) {
            ExcelVo excelVo = list.get(i);
            //创建行
            XSSFRow xssfRow = xssfSheet.createRow(i+1);
            //创建列，即单元格Cell
            XSSFCell xssfCell = xssfRow.createCell(0); //创建单元格
            xssfCell.setCellValue(excelVo.getDistrict());
            //创建列，即单元格Cell
            XSSFCell xssfCell1 = xssfRow.createCell(1); //创建单元格
            xssfCell1.setCellValue(excelVo.getEsn());
            //创建列，即单元格Cell
            XSSFCell xssfCell2 = xssfRow.createCell(2); //创建单元格
            xssfCell2.setCellValue(excelVo.getProjectName());
            //创建列，即单元格Cell
            XSSFCell xssfCell3 = xssfRow.createCell(3); //创建单元格
            xssfCell3.setCellValue(excelVo.getLLocDesc());
            //创建列，即单元格Cell
            XSSFCell xssfCell4 = xssfRow.createCell(4); //创建单元格
            xssfCell4.setCellValue(excelVo.getLArea());
            //创建列，即单元格Cell
            XSSFCell xssfCell5 = xssfRow.createCell(5); //创建单元格
            xssfCell5.setCellValue(excelVo.getLSource());
            //创建列，即单元格Cell
            XSSFCell xssfCell6 = xssfRow.createCell(6); //创建单元格
            xssfCell6.setCellValue(excelVo.getLUseType());
            //创建列，即单元格Cell
            XSSFCell xssfCell7 = xssfRow.createCell(7); //创建单元格
            xssfCell7.setCellValue(excelVo.getLSupplyType());
            //创建列，即单元格Cell
            XSSFCell xssfCell8 = xssfRow.createCell(8); //创建单元格
            xssfCell8.setCellValue(excelVo.getLUseYears());
            //创建列，即单元格Cell
            XSSFCell xssfCell9 = xssfRow.createCell(9); //创建单元格
            xssfCell9.setCellValue(excelVo.getIndustryType());
            //创建列，即单元格Cell
            XSSFCell xssfCell10 = xssfRow.createCell(10); //创建单元格
            xssfCell10.setCellValue(excelVo.getLGrade());
            //创建列，即单元格Cell
            XSSFCell xssfCell11 = xssfRow.createCell(11); //创建单元格
            xssfCell11.setCellValue(excelVo.getFinalPrice());
            //创建列，即单元格Cell
            XSSFCell xssfCell12 = xssfRow.createCell(12); //创建单元格
            xssfCell12.setCellValue(excelVo.getLandUser());
            //创建列，即单元格Cell
            XSSFCell xssfCell13 = xssfRow.createCell(13); //创建单元格
            xssfCell13.setCellValue(excelVo.getLCapacityRateL());
            //创建列，即单元格Cell
            XSSFCell xssfCell14 = xssfRow.createCell(14); //创建单元格
            xssfCell14.setCellValue(excelVo.getLCapacityRateH());
            //创建列，即单元格Cell
            XSSFCell xssfCell15 = xssfRow.createCell(15); //创建单元格
            xssfCell15.setCellValue(excelVo.getLDeliverDate());
            //创建列，即单元格Cell
            XSSFCell xssfCell16 = xssfRow.createCell(16); //创建单元格
            xssfCell16.setCellValue(excelVo.getPlannedDateS());
            //创建列，即单元格Cell
            XSSFCell xssfCell17 = xssfRow.createCell(17); //创建单元格
            xssfCell17.setCellValue(excelVo.getPlannedDateE());
            //创建列，即单元格Cell
            XSSFCell xssfCell18 = xssfRow.createCell(18); //创建单元格
            xssfCell18.setCellValue(excelVo.getConstructionDateS());
            //创建列，即单元格Cell
            XSSFCell xssfCell19 = xssfRow.createCell(19); //创建单元格
            xssfCell19.setCellValue(excelVo.getConstructionDateE());
            //创建列，即单元格Cell
            XSSFCell xssfCell20 = xssfRow.createCell(20); //创建单元格
            xssfCell20.setCellValue(excelVo.getSeller());
            //创建列，即单元格Cell
            XSSFCell xssfCell21 = xssfRow.createCell(21); //创建单元格
            xssfCell21.setCellValue(excelVo.getContractDate());
            //创建列，即单元格Cell
            XSSFCell xssfCell22 = xssfRow.createCell(22); //创建单元格
            xssfCell22.setCellValue(excelVo.getUrl());

        }
        
        try {
            xssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeTitle(XSSFSheet xssfSheet){
        //创建行
        XSSFRow xssfRow = xssfSheet.createRow(0);
        //创建列，即单元格Cell
        XSSFCell xssfCell = xssfRow.createCell(0); //创建单元格
        xssfCell.setCellValue("行政区");
        //创建列，即单元格Cell
        XSSFCell xssfCell1 = xssfRow.createCell(1); //创建单元格
        xssfCell1.setCellValue("电子监管号");
        //创建列，即单元格Cell
        XSSFCell xssfCell2 = xssfRow.createCell(2); //创建单元格
        xssfCell2.setCellValue("项目名称");
        //创建列，即单元格Cell
        XSSFCell xssfCell3 = xssfRow.createCell(3); //创建单元格
        xssfCell3.setCellValue("项目位置");
        //创建列，即单元格Cell
        XSSFCell xssfCell4 = xssfRow.createCell(4); //创建单元格
        xssfCell4.setCellValue("面积(公顷)");
        //创建列，即单元格Cell
        XSSFCell xssfCell5 = xssfRow.createCell(5); //创建单元格
        xssfCell5.setCellValue("土地来源");
        //创建列，即单元格Cell
        XSSFCell xssfCell6 = xssfRow.createCell(6); //创建单元格
        xssfCell6.setCellValue("土地用途");
        //创建列，即单元格Cell
        XSSFCell xssfCell7 = xssfRow.createCell(7); //创建单元格
        xssfCell7.setCellValue("供地方式");
        //创建列，即单元格Cell
        XSSFCell xssfCell8 = xssfRow.createCell(8); //创建单元格
        xssfCell8.setCellValue("土地使用年限");
        //创建列，即单元格Cell
        XSSFCell xssfCell9 = xssfRow.createCell(9); //创建单元格
        xssfCell9.setCellValue("行业分类");
        //创建列，即单元格Cell
        XSSFCell xssfCell10 = xssfRow.createCell(10); //创建单元格
        xssfCell10.setCellValue("土地级别");
        //创建列，即单元格Cell
        XSSFCell xssfCell11 = xssfRow.createCell(11); //创建单元格
        xssfCell11.setCellValue("成交价格(万元)");
        //创建列，即单元格Cell
        XSSFCell xssfCell12 = xssfRow.createCell(12); //创建单元格
        xssfCell12.setCellValue("土地使用权人");
        //创建列，即单元格Cell
        XSSFCell xssfCell13 = xssfRow.createCell(13); //创建单元格
        xssfCell13.setCellValue("约定容积率下限");
        //创建列，即单元格Cell
        XSSFCell xssfCell14 = xssfRow.createCell(14); //创建单元格
        xssfCell14.setCellValue("约定容积率上限");
        //创建列，即单元格Cell
        XSSFCell xssfCell15 = xssfRow.createCell(15); //创建单元格
        xssfCell15.setCellValue("约定交地时间");
        //创建列，即单元格Cell
        XSSFCell xssfCell16 = xssfRow.createCell(16); //创建单元格
        xssfCell16.setCellValue("约定开工时间");
        //创建列，即单元格Cell
        XSSFCell xssfCell17 = xssfRow.createCell(17); //创建单元格
        xssfCell17.setCellValue("约定竣工时间");
        //创建列，即单元格Cell
        XSSFCell xssfCell18 = xssfRow.createCell(18); //创建单元格
        xssfCell18.setCellValue("实际开工时间");
        //创建列，即单元格Cell
        XSSFCell xssfCell19 = xssfRow.createCell(19); //创建单元格
        xssfCell19.setCellValue("实际竣工时间");
        //创建列，即单元格Cell
        XSSFCell xssfCell20 = xssfRow.createCell(20); //创建单元格
        xssfCell20.setCellValue("批准单位");
        //创建列，即单元格Cell
        XSSFCell xssfCell21 = xssfRow.createCell(21); //创建单元格
        xssfCell21.setCellValue("合同签订日期");
        //创建列，即单元格Cell
        XSSFCell xssfCell22 = xssfRow.createCell(22); //创建单元格
        xssfCell22.setCellValue("网站链接");
    }
}
