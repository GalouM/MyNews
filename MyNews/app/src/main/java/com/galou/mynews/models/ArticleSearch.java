package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleSearch {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("blog")
    @Expose
    private Object blog;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<MutimediaSearch> multimedia = null;
    @SerializedName("headline")
    @Expose
    private HeadlineSearch headline;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = null;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsectoinName")
    @Expose
    private String subsectoinName;
    @SerializedName("byline")
    @Expose
    private Object byline;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private Integer wordCount;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<MutimediaSearch> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MutimediaSearch> multimedia) {
        this.multimedia = multimedia;
    }

    public HeadlineSearch getHeadline() {
        return headline;
    }

    public void setHeadline(HeadlineSearch headline) {
        this.headline = headline;
    }


    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubsectoinName() {
        return subsectoinName;
    }

    public void setSubsectoinName(String subsectoinName) {
        this.subsectoinName = subsectoinName;
    }


}