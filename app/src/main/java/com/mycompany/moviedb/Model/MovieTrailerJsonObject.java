package com.mycompany.moviedb.Model;

import java.util.ArrayList;

/**
 * Created by Arjun Kumar on 09-04-2016.
 */
public class MovieTrailerJsonObject {

    private int id;

    private ArrayList<MovieTrailer> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MovieTrailer> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieTrailer> results) {
        this.results = results;
    }
}
