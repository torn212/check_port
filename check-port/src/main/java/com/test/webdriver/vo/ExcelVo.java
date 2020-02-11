package com.test.webdriver.vo;

import lombok.Data;

@Data
public class ExcelVo {
    //行政区
    private String district;
    //电子监管号
    private String esn;
    //项目名称
    private String projectName;
    //项目位置
    private String lLocDesc;
    //面积(公顷)
    private String lArea;
    //土地来源
    private String lSource;
    //土地用途
    private String lUseType;
    //供地方式
    private String lSupplyType;
    //土地使用年限
    private String lUseYears;
    //行业分类
    private String industryType;
    //土地级别
    private String lGrade;
    //成交价格(万元)
    private String finalPrice;
    //土地使用权人
    private String landUser;
    //约定容积率下限
    private String lCapacityRateL;
    //约定容积率上限
    private String lCapacityRateH;
    //约定交地时间
    private String lDeliverDate;
    //约定开工时间
    private String plannedDateS;
    //约定竣工时间
    private String plannedDateE;
    //实际开工时间
    private String constructionDateS;
    //实际竣工时间
    private String constructionDateE;
    //批准单位
    private String seller;
    //合同签订日期
    private String contractDate;
    //url
    private String url;





















}
