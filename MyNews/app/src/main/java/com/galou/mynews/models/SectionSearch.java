package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionSearch {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private ResponseSearch response;

    public ResponseSearch getResponse() {
        return response;
    }

    public void setResponse(ResponseSearch response) {
        this.response = response;
    }

}