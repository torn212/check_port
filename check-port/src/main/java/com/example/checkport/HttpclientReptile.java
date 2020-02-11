package com.example.checkport;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpclientReptile {

    public static void main(String[] args) throws IOException {
        String loginUrl = "https://www.landchina.com/DesktopModule/BizframeExtendMdl/workList/bulWorkView.aspx?wmguid=20aae8dc-4a0c-4af5-aedf-cc153eb6efdf&recorderguid=JYXT_ZJGG_12128&sitePath="; //获取请求连接
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(loginUrl);


        httpClient.executeMethod(post);

        String html = post.getResponseBodyAsString();
        System.out.println("======" + html);

    }
}
