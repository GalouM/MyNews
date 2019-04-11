package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSearch {

    @SerializedName("docs")
    @Expose
    private List<ArticleSearch> docs = null;
    @SerializedName("meta")
    @Expose
    private MetaSearch meta;

    public List<ArticleSearch> getDocs() {
        return docs;
    }

    public void setDocs(List<ArticleSearch> docs) {
        this.docs = docs;
    }

    public MetaSearch getMeta() {
        return meta;
    }

    public void setMeta(MetaSearch meta) {
        this.meta = meta;
    }
}