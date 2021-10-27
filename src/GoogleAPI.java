package com.example.javafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleAPI {
    public static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        // https://script.google.com/macros/s/AKfycbz_CB_SDnJunNHaO9DDDYLCFMizXXmcq0rgKnkpWI4LQ7VMemO_T97QuOaBd9y28-N4xg/exec
        // https://script.google.com/macros/s/AKfycbzmIK8nAhL-yhHIwjspdqaC8pc_4PvkEnjxljZS3rlm9u6fKgg/exec
        String urlStr = "https://script.google.com/macros/s/AKfycbzmIK8nAhL-yhHIwjspdqaC8pc_4PvkEnjxljZS3rlm9u6fKgg/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) {
        String text = "Hello world!";
        //Translated text: Hallo Welt!
        try {
            System.out.println("Translated text: " + translate("en", "vi", text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
