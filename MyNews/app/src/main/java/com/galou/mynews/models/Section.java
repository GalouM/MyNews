package com.galou.mynews.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private long numResults;
    @SerializedName("results")
    @Expose
    private List<Article> results = null;


    public long getNumResults() {
        return numResults;
    }

    public void setNumResults(long numResults) {
        this.numResults = numResults;
    }

    public List<Article> getResults() {
        return results;
    }

    public void setResults(List<Article> results) {
        this.results = results;
    }

}