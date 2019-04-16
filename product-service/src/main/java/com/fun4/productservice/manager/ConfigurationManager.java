package com.fun4.productservice.manager;

import com.fun4.productservice.model.Product;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class ConfigurationManager {

    Gson gson = new GsonBuilder().create();

    public Configuration getConfiguration() {
        //TODO Change this with http call and setting configs from that

        String url = "http://localhost:9005/read/service.product";
        Configuration config = new Configuration();

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String[] arr = Arrays.stream(response.substring(1, response.length() - 1).split(","))
                    .map(e -> e.replaceAll("\"", ""))
                    .toArray(String[]::new);

            int i = 0;

            for (String s : arr) {
                String[] parts = s.split(":", 2);
                config.setProperty(parts[0], parts[1]);
                i++;
            }

            config.addAnnotatedClass(Product.class);

        } catch (Exception ex) {
            System.out.println("expection: " + ex.getMessage());
        }


        return config;
    }

}
