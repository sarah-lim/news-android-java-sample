package com.slim.newsdaily.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsPage {
    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;

    @SerializedName("articles")
    public List<Articles> articles;
}
