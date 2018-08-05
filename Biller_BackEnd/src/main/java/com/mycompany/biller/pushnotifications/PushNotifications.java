/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.pushnotifications;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Rbab3a
 */
public class PushNotifications {

    private String message;
    private String title;
    private Long id;
    private Long partyId;
    private String templateId;
    private String filedName;
    private ArrayList userLoginList;

    public PushNotifications(String message, String title, Long id, String filedName, Long partyId, String templateId, ArrayList userLoginList) {
        this.message = message;
        this.title = title;
        this.id = id;
        this.filedName = filedName;
        this.partyId = partyId;
        this.templateId = templateId;
        this.userLoginList = userLoginList;
    }

    public PushNotifications(String message, String title, ArrayList userLoginList) {
        this.message = message;
        this.title = title;
        this.userLoginList = userLoginList;
    }
    


    public void sendMessage() {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic ZjBjOGU3ZDQtODhjNS00MjRjLWJmNWMtY2UwMjIwNzExMDBh");
            con.setRequestMethod("POST");

//            String strJsonBody = "{"
//                    + "\"app_id\": \"e942708c-95ca-455a-ad8e-d7cf50a66466\","
//                    + "\"included_segments\": [\"All\"],"
//                    + "\"url\": \" "+this.url+"\","
//                    + "\"contents\": {\"en\": \"" + this.message + "\"},"
//                    + "\"filters\": [{\"field\": \"tag\", \"key\": \"userLoginId\", \"relation\": \"=\", \"value\": 58}]"
//                    + "}";
//                  String strJsonBody = "{"
//                    + "\"app_id\": \"e942708c-95ca-455a-ad8e-d7cf50a66466\","
//                    + "\"included_segments\": [\"All\"],"
//                    + "\"contents\": {\"ar\": \"" + this.message + "\"},"
//                    + "\"filters\": [{\"field\": \"tag\", \"key\": \"userLoginId\", \"relation\": \"=\", \"value\": 58}],"
//                    + " \"headings\":{\"en\": \""+this.title+"\"},"
//                    + "\"data\":{\"templateId\": \"CUSTOM\","
//                          + "   \"invoiceId\": 602,"
//                          + "  \"partyIdByNotification\":25}}";
            JSONObject contents = new JSONObject();
            contents.put("en", this.message);
//        contents.put("en", "You have new invoice");

            JSONArray filters = new JSONArray();
            JSONObject filterObj = null;
            //{"operator": "OR"}
            JSONObject operator = null;
            System.out.println("this.userLoginList.size =" + this.userLoginList.size());

            for (int i = 0; i < this.userLoginList.size(); i++) {
                filterObj = new JSONObject();
                filterObj.put("field", "tag");
                filterObj.put("key", "userLoginId");
                filterObj.put("relation", "=");
                filterObj.put("value", this.userLoginList.get(i));
                System.out.println("this.userLoginId= " + this.userLoginList.get(i));
                filters.put(filterObj);
                if (i != this.userLoginList.size() - 1) {
                    operator = new JSONObject();
                    operator.put("operator", "OR");
                    filters.put(operator);
                }
            }
//            filters.remove(this.userLoginList.size());

            System.out.println("filters = " + filters);
            System.out.println("filters size= " + filters.length());
            JSONObject headings = new JSONObject();
            headings.put("en", this.title);

            JSONObject data = new JSONObject();
            data.put("templateId", this.templateId);
            data.put(this.filedName, this.id);
            data.put("partyIdByNotification", this.partyId);

            String included_segments[] = {"ALL"};

            JSONObject mainObj = new JSONObject();
            mainObj.put("app_id", "e942708c-95ca-455a-ad8e-d7cf50a66466");

            mainObj.put("included_segments", included_segments);
            mainObj.put("contents", contents);
            mainObj.put("filters", filters);
            mainObj.put("headings", headings);
            mainObj.put("data", data);
//        System.out.println(mainObj.toString());
            String strJsonBody = mainObj.toString();
            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

        public void sendNotificatins() {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic ZjBjOGU3ZDQtODhjNS00MjRjLWJmNWMtY2UwMjIwNzExMDBh");
            con.setRequestMethod("POST");
            JSONObject contents = new JSONObject();
            contents.put("en", this.message);
            JSONArray filters = new JSONArray();
            JSONObject filterObj = null;
            JSONObject operator = null;
            System.out.println("this.userLoginList.size =" + this.userLoginList.size());

            for (int i = 0; i < this.userLoginList.size(); i++) {
                filterObj = new JSONObject();
                filterObj.put("field", "tag");
                filterObj.put("key", "userLoginId");
                filterObj.put("relation", "=");
                filterObj.put("value", this.userLoginList.get(i));
                System.out.println("this.userLoginId= " + this.userLoginList.get(i));
                filters.put(filterObj);
                if (i != this.userLoginList.size() - 1) {
                    operator = new JSONObject();
                    operator.put("operator", "OR");
                    filters.put(operator);
                }
            }
            System.out.println("filters = " + filters);
            System.out.println("filters size= " + filters.length());
            JSONObject headings = new JSONObject();
            headings.put("en", this.title);
            String included_segments[] = {"ALL"};

                  JSONObject data = new JSONObject();
            data.put("templateId", "CUSTOM");
            data.put("invoiceId", 0);
            data.put("partyIdByNotification", 0);
            
            
            JSONObject mainObj = new JSONObject();
            mainObj.put("app_id", "e942708c-95ca-455a-ad8e-d7cf50a66466");

            mainObj.put("included_segments", included_segments);
            mainObj.put("contents", contents);
            mainObj.put("filters", filters);
            mainObj.put("headings", headings);
            mainObj.put("data", data);
            String strJsonBody = mainObj.toString();
            System.out.println("strJsonBody:\n" + strJsonBody);
            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
