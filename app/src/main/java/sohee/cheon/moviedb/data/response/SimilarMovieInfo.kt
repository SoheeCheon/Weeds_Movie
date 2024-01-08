package sohee.cheon.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class SimilarMovieInfo(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieInfo>
)
