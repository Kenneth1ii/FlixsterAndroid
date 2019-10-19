package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String backdropPath;
    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject JsonObject) throws JSONException {   // constructor
        posterPath = JsonObject.getString("poster_path");
        backdropPath = JsonObject.getString("backdrop_path");
        title = JsonObject.getString("title");
        overview = JsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {  // method , return list<Movie>
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length() ; i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i))); // Array of Objects {Objects:1,Object:2 of tuples almost(key,pairs },{} ,{}
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
