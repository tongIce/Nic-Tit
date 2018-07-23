package com.litt.micro.util.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//数据格式为json发送post请求
public class PostWithJson {
	
   public static String JsonSMS(String postUrl, String postData) {
	    try {
	        //发送POST请求
	        URL url = new URL(postUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Connection", "Keep-Alive");
	        conn.setUseCaches(false);
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Length", "" + postData.length());
	        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	        out.write(postData);
	        out.flush();
	        out.close();
	        //获取响应状态
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            System.out.println("connect failed!");
	            return "";
	        }
	        //获取响应内容体
	        String line, result = "";
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	        while ((line = in.readLine()) != null) {
	            result += line + "\n";
	        }
	        in.close();
	        return result;
	    } catch (IOException e) {
	        e.printStackTrace(System.out);
	    }
	    return "";
	}
   
}
