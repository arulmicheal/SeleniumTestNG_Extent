/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.selenium.TestNG.util.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author arulprak
 */
public class RestWebserviceRequest {

    private HttpsURLConnection httpConnection;
    private OutputStream outputStream;
    private String strResponse = "";
    private int responsecode;

    public void httpRequest(String strRequestUrl, String strMethod, String strRequestBody,
    HashMap<String,String> mapHeaders, HashMap<String,String> mapParams) throws Exception {
        URL url = new URL(strRequestUrl);
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        httpConnection = (HttpsURLConnection) url.openConnection();
        httpConnection.setDoInput(true);
        httpConnection.setRequestProperty("Content-Type",
                "application/json");
        switch (strMethod.toLowerCase()) 
            {
                case "get":
                    sendGET();
                    break;
                default:
                    sendPOST(strRequestBody);
            };
        setResponse();
    }

    private void sendGET()
            throws Exception {
        httpConnection.setDoOutput(false);
    }
    private void sendPOST(String strRequestBody)
            throws Exception {
        httpConnection.setDoOutput(true);
        outputStream = httpConnection.getOutputStream();
        outputStream.write(strRequestBody.getBytes());
        outputStream.close();
        outputStream.flush();
    }
    public String getResponse()
    {
        return strResponse;
    }
    public int getResponseCode()
    {
        return responsecode;
    }
    private void setResponse() throws Exception {
        //Checking server return status code
        responsecode = httpConnection.getResponseCode();
        StringBuilder sb= new StringBuilder();
        String strLine="";
        if (responsecode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConnection.getInputStream()));
            
            while ((strLine=reader.readLine()) != null) {
                sb.append(strLine);
            }
            reader.close();
            httpConnection.disconnect();
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConnection.getErrorStream()));
            while ((strLine=reader.readLine()) != null) {
                sb.append(strLine);
            }
            reader.close();
        }
        strResponse = sb.toString();
    }
    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                {
                    //No need to implement.
                }
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                {
                    //No need to implement.
                }
            }
    };
}
