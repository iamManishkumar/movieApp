package com.movie.service;

import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@Service
public class MovieTrailer {
    public String getMovieTrailer(String movieName) {

        String apiKey = "7d805827b870c53c58912344d06df03b";  // Replace with your actual TMDb API key
        String movieTitle = movieName; // Movie title with spaces replaced by '%20'
        int movieId = -1;
        try {
            // Step 1: Search for the movie
            String searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + movieTitle;
            URL url = new URL(searchUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            String searchResponse = response.toString();
            // Parse searchResponse and extract the movie ID

             // Initialize with a default value
            if (searchResponse != null) {
                // Assuming you want the first movie in the search results
                String[] searchResults = searchResponse.split("\"results\":");
                if (searchResults.length > 1) {
                    String resultsJson = searchResults[1];
                    String[] movieInfo = resultsJson.split("\"id\":");
                    if (movieInfo.length > 1) {
                        String idString = movieInfo[1].split(",")[0];
                        movieId = Integer.parseInt(idString);
                    }
                }
            }

            if (movieId != -1) {
                // Print the movie ID
                System.out.println("Movie ID for '" + movieTitle + "': " + movieId);
            } else {
                System.out.println("Movie not found in search results.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        String jsonResponse = "";
        try {
            String apiUrl = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=" + apiKey;
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                jsonResponse = response.toString();
                System.out.println(jsonResponse);
            } else {
                System.out.println("Error: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String videoLink ="";
         // Replace with the provided JSON

        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(jsonResponse);

        if (rootElement.isJsonObject()) {
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonArray resultsArray = rootObject.getAsJsonArray("results");

            Gson gson = new Gson();
            for (JsonElement resultElement : resultsArray) {
                JsonObject resultObject = resultElement.getAsJsonObject();
                String videoKey = resultObject.get("key").getAsString();
                videoLink = "https://www.youtube.com/watch?v=" + videoKey;
                break;

            }
        }
        return videoLink;

    }
}
