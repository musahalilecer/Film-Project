package com.musahalilecer.filmandseriesproject.model

import com.google.gson.annotations.SerializedName

data class FilmModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("language")
    val language: String
) {
}

/*  @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("language")
    val language: String)

   */