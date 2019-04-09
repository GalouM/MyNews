package com.galou.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.List;

public class ArticleTopStories{

    @SerializedName("url")
    @Expose
    protected String url;
    @SerializedName("adx_keywords")
    @Expose
    protected String adxKeywords;
    @SerializedName("subsection")
    @Expose
    protected String subsection;
    @SerializedName("column")
    @Expose
    protected Object column;
    @SerializedName("section")
    @Expose
    protected String section;
    @SerializedName("byline")
    @Expose
    protected String byline;
    @SerializedName("type")
    @Expose
    protected String type;
    @SerializedName("title")
    @Expose
    protected String title;
    @SerializedName("abstract")
    @Expose
    protected String _abstract;
    @SerializedName("published_date")
    @Expose
    protected String publishedDate;
    @SerializedName("source")
    @Expose
    protected String source;
    @SerializedName("id")
    @Expose
    protected long id;
    @SerializedName("asset_id")
    @Expose
    protected long assetId;
    @SerializedName("views")
    @Expose
    protected long views;
    @SerializedName("des_facet")
    @Expose
    protected Object desFacet = null;
    @SerializedName("org_facet")
    @Expose
    protected Object orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    protected Object perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    protected Object geoFacet = null;
    @SerializedName("uri")
    @Expose
    protected String uri;

    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> media = null;

    public List<Multimedia> getMedia() {
        return media;

    }

    public void setMedia(List<Multimedia> media) {
        this.media = media;

    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }



}