package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by galou on 2019-04-08
 */
public class SectionTopStories {
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
    private List<ArticleTopStories> results = null;


    public long getNumResults() {
        return numResults;
    }

    public void setNumResults(long numResults) {
        this.numResults = numResults;
    }

    public List<ArticleTopStories> getResults() {
        return results;
    }

    public void setResults(List<ArticleTopStories> results) {
        this.results = results;
    }
}
