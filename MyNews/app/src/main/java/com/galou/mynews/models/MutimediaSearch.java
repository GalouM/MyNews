package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by galou on 2019-04-09
 */
public class MutimediaSearch {
    @SerializedName("rank")
    @Expose
    private long rank;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("height")
    @Expose
    private long height;
    @SerializedName("width")
    @Expose
    private long width;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("legacy")
    @Expose
    private Object legacy;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

