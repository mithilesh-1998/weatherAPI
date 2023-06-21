package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = null;
        HttpURLConnection connection = null ;
        int responseCode = 0;
        String urlString = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m";

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("problem in url");
        }
        //connection

        try {
             connection = (HttpURLConnection) url.openConnection();
             responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("connection problem");
        }

        //extract information from the connection object:

        if(responseCode == 200){
            BufferedReader in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();

            String readLine = null;

            while((readLine = in.readLine())!=null){
                apiData.append(readLine);
            }
            //
            in.close();

            JSONObject jsonApIResponse = new JSONObject(apiData.toString());

            System.out.println(jsonApIResponse.get("latitude"));
            System.out.println(jsonApIResponse.get("longitude"));
            System.out.println(jsonApIResponse.get("generationtime_ms"));
            System.out.println(jsonApIResponse.get("utc_offset_seconds"));
            System.out.println(jsonApIResponse.get("timezone"));
            System.out.println(jsonApIResponse.get("timezone_abbreviation"));
            System.out.println(jsonApIResponse.get("elevation"));



        }else{
            System.out.println("API call could not be made!!!");
        }

    }
}