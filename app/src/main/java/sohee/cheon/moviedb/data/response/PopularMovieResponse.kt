package sohee.cheon.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results : Array<MovieInfo>,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int
)
