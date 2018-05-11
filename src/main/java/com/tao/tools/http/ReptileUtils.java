package com.tao.tools.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 爬虫工具
 */
public class ReptileUtils {

    public void send(){
        BufferedReader reader=null;
        try {
            URL url=new URL("https://www.cnblogs.com/qianzf/p/6796588.html");

            URLConnection con=url.openConnection();

            reader=new BufferedReader(new InputStreamReader(con.getInputStream()));

            String buf=null;
            while ((buf=reader.readLine())!=null){
                System.out.println(buf);
            }
            System.out.println(con.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        read();
    }


    public static void read(){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String url="http://www.baidu.com";
        BufferedReader reader=null;

        HttpGet request=new HttpGet(url);

        try {
            CloseableHttpResponse response=httpClient.execute(request);


            HttpEntity result=response.getEntity();
            System.out.println(response.getStatusLine());
            System.out.println(response.getLocale());
            System.out.println(response.getProtocolVersion());
            reader=new BufferedReader(new InputStreamReader(result.getContent()));

            String buf=null;
            while ((buf=reader.readLine())!=null){
                System.out.println(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
