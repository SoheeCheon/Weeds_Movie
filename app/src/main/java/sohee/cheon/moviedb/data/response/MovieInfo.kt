package sohee.cheon.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class MovieInfo(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("genre_ids")
    val genreIds : Array<Int>,
    @SerializedName("id")
    val id : Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("popularity")
    val popularity:
)
