package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SectionMostPopular {

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
    private List<ArticleMostPopular> results = null;


    public long getNumResults() {
        return numResults;
    }

    public void setNumResults(long numResults) {
        this.numResults = numResults;
    }

    public List<ArticleMostPopular> getResults() {
        return results;
    }

    public void setResults(List<ArticleMostPopular> results) {
        this.results = results;
    }

}