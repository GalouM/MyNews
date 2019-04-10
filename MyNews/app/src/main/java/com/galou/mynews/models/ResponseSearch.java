package com.galou.mynews.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSearch {

    @SerializedName("docs")
    @Expose
    private List<ArticleSearch> docs = null;
    @SerializedName("meta")
    @Expose
    private Object meta;

    public List<ArticleSearch> getDocs() {
        return docs;
    }

    public void setDocs(List<ArticleSearch> docs) {
        this.docs = docs;
    }

}