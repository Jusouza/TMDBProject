package com.example.tmdbproject.network.response

import com.example.tmdbproject.model.MoviesModel
import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results")
    var results: List<MoviesModel>
)