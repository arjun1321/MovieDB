package com.mycompany.moviedb.Model;

import java.util.ArrayList;

/**
 * Created by Arjun Kumar on 10-04-2016.
 */
public class TvJsonObject {
    private int page;
    private ArrayList<Tv> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Tv> getResults() {
        return results;
    }

    public void setResults(ArrayList<Tv> results) {
        this.results = results;
    }
}
