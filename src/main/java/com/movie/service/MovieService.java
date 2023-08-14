package com.movie.service;

import com.movie.model.MovieRating;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 7d805827b870c53c58912344d06df03b
@Service
public class MovieService {



    public String getMovieRating(String movieName) {
         String apiUrl = "https://www.omdbapi.com/?t=" +movieName+ "&apikey=58a06efd";
        String r="";
        try{
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response data
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Process the data as needed
                String responseData = response.toString();
                System.out.println("API Response: " + responseData);
                return responseData;
            } else {
                System.out.println("API Request failed. Response code: " + responseCode);

            }


            // Close the connection
            connection.disconnect();

        } catch(IOException e){
            e.printStackTrace();
        }
        return r;
    }


    public String getMovieGenre(String genre) {
        String apiUrl = "https://www.omdbapi.com/?apikey=58a06efd&s="+genre;
        String r="";
        try{
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response data
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Process the data as needed
                String responseData = response.toString();
                System.out.println("API Response: " + responseData);
                return responseData;
            } else {
                System.out.println("API Request failed. Response code: " + responseCode);

            }


            // Close the connection
            connection.disconnect();

        } catch(IOException e){
            e.printStackTrace();
        }
        return r;
    }


}
