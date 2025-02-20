package com.example.tmdbproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null
)