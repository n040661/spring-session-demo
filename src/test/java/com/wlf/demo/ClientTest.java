package com.wlf.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;


public class ClientTest {

	@Test
	public void getSessionTest() throws Exception{
		String url="http://localhost:8888/rest/user";
		CloseableHttpClient httpclient=HttpClients.createDefault();
        HttpGet httpget=null;
        httpget=new HttpGet(url);
        httpget.addHeader("x-auth-token","7cf9c14b-4fa2-4893-9563-b66db306cc2d");
        CloseableHttpResponse response=httpclient.execute(httpget);
        Header[] header=response.getHeaders("x-auth-token");
        String token="";
        if(header.length>0){
        	token=header[0].getValue();
        	System.out.println("获得token:"+token);
        }else{
        	System.out.println("没有返回token");
        }
        String rs=parseResponse(response);
        System.out.println(rs);
	}
	
	//@Test
	public void loginTest() throws Exception{
		String url="http://localhost:8888/login/admin/123456";
		CloseableHttpClient httpclient=HttpClients.createDefault();
        HttpPost httppost=new HttpPost(url);
        CloseableHttpResponse response=httpclient.execute(httppost);
        Header[] header=response.getHeaders("x-auth-token");
        String token="";
        if(header.length>0){
        	token=header[0].getValue();
        	System.out.println("获得token:"+token);
        }else{
        	System.out.println("没有返回token");
        }
        String rs=parseResponse(response);
        System.out.println(rs);
	}
	
	public static String parseResponse(HttpResponse response) throws UnsupportedOperationException, IOException{
        String rs="";
        HttpEntity entity=response.getEntity();
        if(entity!=null){
            InputStream instream=entity.getContent();
            rs=convertStreamToString(instream);
        }
        return rs;
    }
	
	public static String convertStreamToString(InputStream is){
        StringBuilder sb=new StringBuilder();
        byte[] bytes=new byte[4096];
        int size=0;
        
        try{
            while((size=is.read(bytes))>0){
                String str=new String(bytes,0,size,"UTF-8");
                sb.append(str);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
	
}
